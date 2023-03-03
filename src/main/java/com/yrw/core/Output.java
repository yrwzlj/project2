package com.yrw.core;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.yrw.entity.MatchResult;
import com.yrw.entity.Player;
import com.yrw.util.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Output {

    private Map<String,String> playerIdNameMap = new HashMap<>();

    private List<Player> playerList = new ArrayList<>();

    private Map<String, JSONObject> teamIdMap = new HashMap<>();

    public void outputPlayers(boolean exportXls,BufferedWriter bufferedWriter) throws IOException{
        bufferedWriter.write("players: /n");
        if (!playerList.isEmpty()) {
            try {
                for (Player p : playerList) {
                    bufferedWriter.write(p.toString());
                }
                bufferedWriter.flush();
                if (exportXls) {
                    FileUtil.exportXls(playerList, Player.class,"参赛选手");
                }
            } catch (IOException e) {
                throw e;
            }
            return;
        }
        playersCache();
        // 输出txt
        try {
            for (Player p : playerList) {
                bufferedWriter.write(p.toString());
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            throw e;
        }
        // 输出excel
        if (exportXls) {
            FileUtil.exportXls(playerList, Player.class,"参赛选手");
        }
    }

    public void playersCache() {
        //解析文件
        String path = "src/data/jsons/players.json";
        File jsonFile = new File(path);
        String jsonData = FileUtil.getStr(jsonFile);
        //转json对象
        JSONObject parse = (JSONObject) JSONObject.parse(jsonData);
        JSONArray players = parse.getJSONArray("players");
        //存入list和map
        for (Object player : players) {
            JSONObject JSONplayer = (JSONObject) player;
            Player p = JSONplayer.to(Player.class);
            playerList.add(p);
            playerIdNameMap.put(p.getUuid(),p.getShortName());
        }
    }

    public void teamsCache(JSONObject parse) {
        JSONArray teams = parse.getJSONArray("teams");

        for (Object team : teams) {
            JSONObject JSONteam = (JSONObject)team;
            teamIdMap.put((String) JSONteam.get("uuid"),JSONteam);
        }
    }

    public void outputResultByDate(String searchDate,boolean exportXls,BufferedWriter bufferedWriter) throws IOException{
        try {
            bufferedWriter.write(searchDate + "比赛结果: \n");
            String path = "src/data/jsons/" + searchDate +".json";
            File jsonFile = new File(path);
            String jsonData = FileUtil.getStr(jsonFile);
            //转json对象
            JSONObject parse = (JSONObject) JSONObject.parse(jsonData);
            //获取主要数据
            JSONArray matches = parse.getJSONArray("matches");

            if (teamIdMap.isEmpty()){
                teamsCache(parse);
            }

            if (playerIdNameMap.isEmpty()) {
                playersCache();
            }

            List<MatchResult> matchResultList = new ArrayList<>();
            //挨个遍历
            for (Object match : matches) {
                JSONObject jsonMatch = (JSONObject) match;
                String date = (String) jsonMatch.get("date");

                if (date.equals("2023-01-16")) {
                    String startTime = (String) jsonMatch.get("actual_start_time");
                    String winner = "winner:";
                    JSONArray team = jsonMatch.getJSONArray("teams");

                    JSONObject team1 = (JSONObject)team.get(0);
                    JSONObject team2 = (JSONObject)team.get(1);

                    JSONObject winTeam = team1.containsKey("status") ? team1 : team2;

                    String team_id = (String)winTeam.get("team_id");
                    JSONObject teamJSON = teamIdMap.get(team_id);
                    JSONArray playerIds = teamJSON.getJSONArray("players");

                    for (int i = 0;i < playerIds.size();i++) {
                        Object playerId = playerIds.get(i);
                        String playerName = playerIdNameMap.get((String) playerId);
                        if (i > 0) {
                            winner += "&";
                        }
                        winner += playerName;
                    }

                    JSONArray team1Scores = team1.getJSONArray("score");
                    JSONArray team2Scores = team2.getJSONArray("score");
                    //String[] scores = new String[team1Scores.size()];
                    String strScore = "score:";

                    for (int i = 0;i < team1Scores.size();i++) {
                        JSONObject score1 = (JSONObject)team1Scores.get(i);
                        JSONObject score2 = (JSONObject)team2Scores.get(i);
                        //scores[i] = score1.get("game") + ":" + score2.get("game");
                        if (i > 0) {
                            strScore += " | ";
                        }
                        strScore = strScore + score1.get("game") + ":" + score2.get("game");
                    }
                    MatchResult matchResult = new MatchResult("2023-01-16",startTime,winner.substring(7),strScore.substring(6));
                    matchResultList.add(matchResult);
                    bufferedWriter.write(matchResult.toString());
                }
            }
            bufferedWriter.flush();
            if (exportXls) {
                FileUtil.exportXls(matchResultList, MatchResult.class,"比赛结果");
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
