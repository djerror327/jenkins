package com.dinusha.soft.smonitor.service;

import com.dinusha.soft.smonitor.utills.JsonUtil;
import com.dinusha.soft.smonitor.utills.Paginate;
import com.dinusha.soft.smonitor.utills.SonarAuthHeader;
import com.dinusha.soft.smonitor.webclient.Client;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class SonarProjectService {

    private static final Logger logger = Logger.getLogger(SonarProjectService.class);

    @Autowired
    private Paginate paginate;
    @Autowired
    private JsonUtil jsonUtil;
    @Autowired
    private Client client;
    @Autowired
    private SonarAuthHeader sonarAuthHeaderService;
    @Value("${sonar.host}")
    private String host;

    //return all SonarQube project keys
    public final Supplier<List<String>> getProjects = () -> {
        //paging related part
        logger.debug("Reading paging sizes");
        String pagingData = client.getWithAuthHeader.apply(sonarAuthHeaderService.authHeader.get(), host + "api/projects/search?ps=500");
        JSONObject pageObj = jsonUtil.stringToJsonObject.apply(pagingData);

        //calculate paging count
        JSONObject paging = (JSONObject) pageObj.get("paging");
        long recursionCount = paginate.recursionCount.applyAsLong(paging);

        ArrayList<String> projectKeys = new ArrayList<>();
        //loop all pages and collect violation data
        logger.info("Reading project list from SonarQube");
        for (int page = 1; page <= recursionCount; page++) {
            String projects = client.getWithAuthHeader.apply(sonarAuthHeaderService.authHeader.get(), host + "api/projects/search?ps=500&p=" + page + "");
            JSONObject jsonProjects = jsonUtil.stringToJsonObject.apply(projects);
            JSONArray issueArr = (JSONArray) jsonProjects.get("components");
            for (Object project : issueArr) {
                JSONObject projectObj = (JSONObject) project;
                String projectKey = projectObj.get("key").toString();
                projectKeys.add(projectKey);
            }
        }
        logger.debug("Reading project list from SonarQube is completed!");
        return projectKeys;
    };
}
