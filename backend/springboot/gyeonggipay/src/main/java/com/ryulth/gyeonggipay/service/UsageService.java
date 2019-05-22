package com.ryulth.gyeonggipay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryulth.gyeonggipay.dto.Usage;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UsageService {
    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper;

    @Autowired
    public UsageService(RestHighLevelClient restHighLevelClient,ObjectMapper objectMapper) {
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }
    public String searchByAddress(String address) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("address",address));
        SearchRequest searchRequest = new SearchRequest("gyeonggipay").types("usage").source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        return getSearchResult(searchResponse).toString();
    }

    public String findUsage(String address) throws IOException {
        GetRequest getRequest = new GetRequest("gyeonggipay", "usage", address);

        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();
        System.out.println(resultMap);
        return objectMapper
                .convertValue(resultMap, Usage.class)
                .toString();

    }
    public String findAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
                restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse).toString();
    }
    private List<Usage> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<Usage> profileDocuments = new ArrayList<>();

        if (searchHit.length > 0) {

            Arrays.stream(searchHit)
                    .forEach(hit -> profileDocuments
                            .add(objectMapper
                                    .convertValue(hit.getSourceAsMap(),
                                            Usage.class))
                    );
        }

        return profileDocuments;
    }
}
