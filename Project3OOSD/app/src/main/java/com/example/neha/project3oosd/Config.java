package com.example.neha.project3oosd;


public class Config {
    //Script Address
    public static final String URL_GET_ALL = "http://192.168.134.1/androidapp2/getAllAgents.php";
    public static final String URL_GET_ALL1 = "http://192.168.134.1/androidapp2/getAgents1.php";
    public static final String URL_GET_ALL2 = "http://192.168.134.1/androidapp2/getAgents2.php";

    public static final String URL_GET_AGT = "http://192.168.134.1/androidapp2/getAgent.php?AgentId=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_AGT_ID = "AgentId";
    public static final String KEY_AGT_FNAME = "AgtFirstName";
    public static final String KEY_AGT_LNAME = "AgtLastName";
    public static final String KEY_AGT_PHONE = "AgtBusPhone";
    public static final String KEY_AGT_EMAIL = "AgtEmail";
    public static final String KEY_AGT_POSITION = "AgtPosition";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "AgentId";
    public static final String TAG_FNAME = "AgtFirstName";
    public static final String TAG_LNAME = "AgtLastName";
    public static final String TAG_PHONE = "AgtBusPhone";
    public static final String TAG_EMAIL = "AgtEmail";
    public static final String TAG_POSITION = "AgtPosition";

    //agent id to pass with intent
    public static final String AGT_ID = "agt_id";
}
