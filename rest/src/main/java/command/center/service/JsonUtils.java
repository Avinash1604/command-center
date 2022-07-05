package command.center.service;

import java.util.Optional;

public class JsonUtils {


    public static String leadByTimeCount(String timeFrame) {
      String data = "{\n" +
              "    \"limit\": null,\n" +
              "    \"query\": {\n" +
              "        \"negate\": false,\n" +
              "        \"queries\": [\n" +
              "        {\n" +
              "            \"negate\": false,\n" +
              "            \"object_type\": \"lead\",\n" +
              "            \"type\": \"object_type\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"negate\": false,\n" +
              "            \"queries\": [\n" +
              "            {\n" +
              "                \"negate\": false,\n" +
              "                \"queries\": [\n" +
              "                {\n" +
              "                    \"condition\": {\n" +
              "                    \"before\": {\n" +
              "                        \"range\": \""+timeFrame+"\",\n" +
              "                        \"type\": \"start_end_of_predefined_relative_period\",\n" +
              "                        \"which\": \"end\"\n" +
              "                    },\n" +
              "                    \"on_or_after\": {\n" +
              "                        \"range\": \""+timeFrame+"\",\n" +
              "                        \"type\": \"start_end_of_predefined_relative_period\",\n" +
              "                        \"which\": \"start\"\n" +
              "                    },\n" +
              "                    \"type\": \"moment_range\"\n" +
              "                    },\n" +
              "                    \"field\": {\n" +
              "                    \"field_name\": \"last_lead_status_change_date\",\n" +
              "                    \"object_type\": \"lead\",\n" +
              "                    \"type\": \"regular_field\"\n" +
              "                    },\n" +
              "                    \"negate\": false,\n" +
              "                    \"type\": \"field_condition\"\n" +
              "                }\n" +
              "                ],\n" +
              "                \"type\": \"and\"\n" +
              "            }\n" +
              "            ],\n" +
              "            \"type\": \"and\"\n" +
              "        }\n" +
              "        ],\n" +
              "        \"type\": \"and\"\n" +
              "    },\n" +
              "    \"include_counts\": true,\n" +
              "    \"_limit\": 10,\n" +
              "    \"cursor\": null,\n" +
              "    \"sort\": []\n" +
              "    }";
      return data;
    }

   public static String getJsonByCampaign(String campaignName) {
     return "{\n" +
             "        \"limit\": null,\n" +
             "        \"query\": {\n" +
             "          \"negate\": false,\n" +
             "          \"queries\": [\n" +
             "            {\n" +
             "              \"negate\": false,\n" +
             "              \"object_type\": \"lead\",\n" +
             "              \"type\": \"object_type\"\n" +
             "            },\n" +
             "            {\n" +
             "              \"negate\": false,\n" +
             "              \"queries\": [\n" +
             "                {\n" +
             "                  \"negate\": false,\n" +
             "                  \"queries\": [\n" +
             "                    {\n" +
             "                      \"condition\": {\n" +
             "                        \"mode\": \"full_words\",\n" +
             "                        \"type\": \"text\",\n" +
             "                        \"value\": \""+campaignName+"\"\n" +
             "                      },\n" +
             "                      \"field\": {\n" +
             "                        \"field_name\": \"name\",\n" +
             "                        \"object_type\": \"lead\",\n" +
             "                        \"type\": \"regular_field\"\n" +
             "                      },\n" +
             "                      \"negate\": false,\n" +
             "                      \"type\": \"field_condition\"\n" +
             "                    }\n" +
             "                  ],\n" +
             "                  \"type\": \"and\"\n" +
             "                }\n" +
             "              ],\n" +
             "              \"type\": \"and\"\n" +
             "            }\n" +
             "          ],\n" +
             "          \"type\": \"and\"\n" +
             "        },\n" +
             "        \"results_limit\": null,\n" +
             "        \"sort\": []\n" +
             "      }";
   }


    public static String getJsonByCampaign(String campaignName, String startDate) {
        return "{\n" +
                "        \"limit\": null,\n" +
                "        \"query\": {\n" +
                "            \"negate\": false,\n" +
                "            \"queries\": [\n" +
                "                {\n" +
                "                    \"negate\": false,\n" +
                "                    \"object_type\": \"lead\",\n" +
                "                    \"type\": \"object_type\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"negate\": false,\n" +
                "                    \"queries\": [\n" +
                "                        {\n" +
                "                            \"negate\": false,\n" +
                "                            \"queries\": [\n" +
                "                                {\n" +
                "                                    \"condition\": {\n" +
                "                                        \"mode\": \"full_words\",\n" +
                "                                        \"type\": \"text\",\n" +
                "                                        \"value\": \""+campaignName+"\"\n" +
                "                                    },\n" +
                "                                    \"field\": {\n" +
                "                                        \"field_name\": \"name\",\n" +
                "                                        \"object_type\": \"lead\",\n" +
                "                                        \"type\": \"regular_field\"\n" +
                "                                    },\n" +
                "                                    \"negate\": false,\n" +
                "                                    \"type\": \"field_condition\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"type\": \"and\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"negate\": false,\n" +
                "                            \"queries\": [\n" +
                "                                {\n" +
                "                                    \"condition\": {\n" +
                "                                        \n" +
                "                                        \"on_or_after\": {\n" +
                "                                            \"type\": \"fixed_local_date\",\n" +
                "                                            \"value\": \""+startDate+"\",\n" +
                "                                            \"which\": \"start\"\n" +
                "                                        },\n" +
                "                                        \"type\": \"moment_range\"\n" +
                "                                    },\n" +
                "                                    \"field\": {\n" +
                "                                        \"field_name\": \"last_lead_status_change_date\",\n" +
                "                                        \"object_type\": \"lead\",\n" +
                "                                        \"type\": \"regular_field\"\n" +
                "                                    },\n" +
                "                                    \"negate\": false,\n" +
                "                                    \"type\": \"field_condition\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"type\": \"and\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"type\": \"and\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"type\": \"and\"\n" +
                "        },\n" +
                "        \"results_limit\": null,\n" +
                "        \"sort\": []\n" +
                "    }";
    }

    public static String getJsonByCampaignEndDate(String campaignName, String endDate) {
       return "{\n" +
               "        \"limit\": null,\n" +
               "        \"query\": {\n" +
               "            \"negate\": false,\n" +
               "            \"queries\": [\n" +
               "                {\n" +
               "                    \"negate\": false,\n" +
               "                    \"object_type\": \"lead\",\n" +
               "                    \"type\": \"object_type\"\n" +
               "                },\n" +
               "                {\n" +
               "                    \"negate\": false,\n" +
               "                    \"queries\": [\n" +
               "                        {\n" +
               "                            \"negate\": false,\n" +
               "                            \"queries\": [\n" +
               "                                {\n" +
               "                                    \"condition\": {\n" +
               "                                        \"mode\": \"full_words\",\n" +
               "                                        \"type\": \"text\",\n" +
               "                                        \"value\": \""+campaignName+"\"\n" +
               "                                    },\n" +
               "                                    \"field\": {\n" +
               "                                        \"field_name\": \"name\",\n" +
               "                                        \"object_type\": \"lead\",\n" +
               "                                        \"type\": \"regular_field\"\n" +
               "                                    },\n" +
               "                                    \"negate\": false,\n" +
               "                                    \"type\": \"field_condition\"\n" +
               "                                }\n" +
               "                            ],\n" +
               "                            \"type\": \"and\"\n" +
               "                        },\n" +
               "                        {\n" +
               "                            \"negate\": false,\n" +
               "                            \"queries\": [\n" +
               "                                {\n" +
               "                                    \"condition\": {\n" +
               "                                        \"before\": {\n" +
               "                                            \"type\": \"fixed_local_date\",\n" +
               "                                            \"value\": \""+endDate+"\",\n" +
               "                                            \"which\": \"end\"\n" +
               "                                        },\n" +
               "                                        \"type\": \"moment_range\"\n" +
               "                                    },\n" +
               "                                    \"field\": {\n" +
               "                                        \"field_name\": \"last_lead_status_change_date\",\n" +
               "                                        \"object_type\": \"lead\",\n" +
               "                                        \"type\": \"regular_field\"\n" +
               "                                    },\n" +
               "                                    \"negate\": false,\n" +
               "                                    \"type\": \"field_condition\"\n" +
               "                                }\n" +
               "                            ],\n" +
               "                            \"type\": \"and\"\n" +
               "                        }\n" +
               "                    ],\n" +
               "                    \"type\": \"and\"\n" +
               "                }\n" +
               "            ],\n" +
               "            \"type\": \"and\"\n" +
               "        },\n" +
               "        \"results_limit\": null,\n" +
               "        \"sort\": []\n" +
               "    }";
    }

    public static String getJsonByCampaignByStartAndEndDate(String startDate, String endDate) {
       return "{\n" +
               "      \"limit\": null,\n" +
               "      \"query\": {\n" +
               "          \"negate\": false,\n" +
               "          \"queries\": [\n" +
               "              {\n" +
               "                  \"negate\": false,\n" +
               "                  \"object_type\": \"lead\",\n" +
               "                  \"type\": \"object_type\"\n" +
               "              },\n" +
               "              {\n" +
               "                  \"negate\": false,\n" +
               "                  \"queries\": [\n" +
               "                      {\n" +
               "                          \"negate\": false,\n" +
               "                          \"queries\": [\n" +
               "                              {\n" +
               "                                  \"condition\": {\n" +
               "                                      \"before\": {\n" +
               "                                          \"type\": \"fixed_local_date\",\n" +
               "                                          \"value\": \""+endDate+"\",\n" +
               "                                          \"which\": \"end\"\n" +
               "                                      },\n" +
               "                                      \"on_or_after\": {\n" +
               "                                          \"type\": \"fixed_local_date\",\n" +
               "                                          \"value\": \""+startDate+"\",\n" +
               "                                          \"which\": \"start\"\n" +
               "                                      },\n" +
               "                                      \"type\": \"moment_range\"\n" +
               "                                  },\n" +
               "                                  \"field\": {\n" +
               "                                      \"field_name\": \"last_lead_status_change_date\",\n" +
               "                                      \"object_type\": \"lead\",\n" +
               "                                      \"type\": \"regular_field\"\n" +
               "                                  },\n" +
               "                                  \"negate\": false,\n" +
               "                                  \"type\": \"field_condition\"\n" +
               "                              }\n" +
               "                          ],\n" +
               "                          \"type\": \"and\"\n" +
               "                      }\n" +
               "                  ],\n" +
               "                  \"type\": \"and\"\n" +
               "              }\n" +
               "          ],\n" +
               "          \"type\": \"and\"\n" +
               "      },\n" +
               "      \"results_limit\": null,\n" +
               "      \"sort\": []\n" +
               "  }";
    }

    public static String getJsonByEndDate(String endDate) {
      return "{\n" +
              "      \"limit\": null,\n" +
              "      \"query\": {\n" +
              "          \"negate\": false,\n" +
              "          \"queries\": [\n" +
              "              {\n" +
              "                  \"negate\": false,\n" +
              "                  \"object_type\": \"lead\",\n" +
              "                  \"type\": \"object_type\"\n" +
              "              },\n" +
              "              {\n" +
              "                  \"negate\": false,\n" +
              "                  \"queries\": [\n" +
              "                      {\n" +
              "                          \"negate\": false,\n" +
              "                          \"queries\": [\n" +
              "                              {\n" +
              "                                  \"condition\": {\n" +
              "                                      \"before\": {\n" +
              "                                          \"type\": \"fixed_local_date\",\n" +
              "                                          \"value\": \""+endDate+"\",\n" +
              "                                          \"which\": \"end\"\n" +
              "                                      },\n" +
              "                                      \"type\": \"moment_range\"\n" +
              "                                  },\n" +
              "                                  \"field\": {\n" +
              "                                      \"field_name\": \"last_lead_status_change_date\",\n" +
              "                                      \"object_type\": \"lead\",\n" +
              "                                      \"type\": \"regular_field\"\n" +
              "                                  },\n" +
              "                                  \"negate\": false,\n" +
              "                                  \"type\": \"field_condition\"\n" +
              "                              }\n" +
              "                          ],\n" +
              "                          \"type\": \"and\"\n" +
              "                      }\n" +
              "                  ],\n" +
              "                  \"type\": \"and\"\n" +
              "              }\n" +
              "          ],\n" +
              "          \"type\": \"and\"\n" +
              "      },\n" +
              "      \"results_limit\": null,\n" +
              "      \"sort\": []\n" +
              "  }";
    }

    public static String getJsonByStartDate(String startDate) {
        return "{\n" +
                "      \"limit\": null,\n" +
                "      \"query\": {\n" +
                "          \"negate\": false,\n" +
                "          \"queries\": [\n" +
                "              {\n" +
                "                  \"negate\": false,\n" +
                "                  \"object_type\": \"lead\",\n" +
                "                  \"type\": \"object_type\"\n" +
                "              },\n" +
                "              {\n" +
                "                  \"negate\": false,\n" +
                "                  \"queries\": [\n" +
                "                      {\n" +
                "                          \"negate\": false,\n" +
                "                          \"queries\": [\n" +
                "                              {\n" +
                "                                  \"condition\": {\n" +
                "                                     \n" +
                "                                      \"on_or_after\": {\n" +
                "                                          \"type\": \"fixed_local_date\",\n" +
                "                                          \"value\": \""+startDate+"\",\n" +
                "                                          \"which\": \"start\"\n" +
                "                                      },\n" +
                "                                      \"type\": \"moment_range\"\n" +
                "                                  },\n" +
                "                                  \"field\": {\n" +
                "                                      \"field_name\": \"last_lead_status_change_date\",\n" +
                "                                      \"object_type\": \"lead\",\n" +
                "                                      \"type\": \"regular_field\"\n" +
                "                                  },\n" +
                "                                  \"negate\": false,\n" +
                "                                  \"type\": \"field_condition\"\n" +
                "                              }\n" +
                "                          ],\n" +
                "                          \"type\": \"and\"\n" +
                "                      }\n" +
                "                  ],\n" +
                "                  \"type\": \"and\"\n" +
                "              }\n" +
                "          ],\n" +
                "          \"type\": \"and\"\n" +
                "      },\n" +
                "      \"results_limit\": null,\n" +
                "      \"sort\": []\n" +
                "  }";
    }

    public static String getJsonByCampaignAndStartAndEndDate(String campaignName, String startDate, String endDate) {
      return "{\n" +
              "        \"limit\": null,\n" +
              "        \"query\": {\n" +
              "            \"negate\": false,\n" +
              "            \"queries\": [\n" +
              "                {\n" +
              "                    \"negate\": false,\n" +
              "                    \"object_type\": \"lead\",\n" +
              "                    \"type\": \"object_type\"\n" +
              "                },\n" +
              "                {\n" +
              "                    \"negate\": false,\n" +
              "                    \"queries\": [\n" +
              "                        {\n" +
              "                            \"negate\": false,\n" +
              "                            \"queries\": [\n" +
              "                                {\n" +
              "                                    \"condition\": {\n" +
              "                                        \"mode\": \"full_words\",\n" +
              "                                        \"type\": \"text\",\n" +
              "                                        \"value\": \""+campaignName+"\"\n" +
              "                                    },\n" +
              "                                    \"field\": {\n" +
              "                                        \"field_name\": \"name\",\n" +
              "                                        \"object_type\": \"lead\",\n" +
              "                                        \"type\": \"regular_field\"\n" +
              "                                    },\n" +
              "                                    \"negate\": false,\n" +
              "                                    \"type\": \"field_condition\"\n" +
              "                                }\n" +
              "                            ],\n" +
              "                            \"type\": \"and\"\n" +
              "                        },\n" +
              "                        {\n" +
              "                            \"negate\": false,\n" +
              "                            \"queries\": [\n" +
              "                                {\n" +
              "                                    \"condition\": {\n" +
              "                                        \"before\": {\n" +
              "                                            \"type\": \"fixed_local_date\",\n" +
              "                                            \"value\": \""+endDate+"\",\n" +
              "                                            \"which\": \"end\"\n" +
              "                                        },\n" +
              "                                        \"on_or_after\": {\n" +
              "                                            \"type\": \"fixed_local_date\",\n" +
              "                                            \"value\": \""+startDate+"\",\n" +
              "                                            \"which\": \"start\"\n" +
              "                                        },\n" +
              "                                        \"type\": \"moment_range\"\n" +
              "                                    },\n" +
              "                                    \"field\": {\n" +
              "                                        \"field_name\": \"last_lead_status_change_date\",\n" +
              "                                        \"object_type\": \"lead\",\n" +
              "                                        \"type\": \"regular_field\"\n" +
              "                                    },\n" +
              "                                    \"negate\": false,\n" +
              "                                    \"type\": \"field_condition\"\n" +
              "                                }\n" +
              "                            ],\n" +
              "                            \"type\": \"and\"\n" +
              "                        }\n" +
              "                    ],\n" +
              "                    \"type\": \"and\"\n" +
              "                }\n" +
              "            ],\n" +
              "            \"type\": \"and\"\n" +
              "        },\n" +
              "        \"results_limit\": null,\n" +
              "        \"sort\": []\n" +
              "    }";
    }

   public static String getLeadFilters(Optional<String> campaignName, Optional<String> startDate, Optional<String> endDate) {
        if (campaignName.isPresent() && startDate.isPresent() && endDate.isPresent()){
            return JsonUtils.getJsonByCampaignAndStartAndEndDate(campaignName.get(), startDate.get(), endDate.get());
        }
        else if(campaignName.isPresent() && startDate.isPresent()){
            return JsonUtils.getJsonByCampaign(campaignName.get(), startDate.get());
        }
        else if(campaignName.isPresent() && endDate.isPresent()){
            return JsonUtils.getJsonByCampaignEndDate(campaignName.get(), endDate.get());
        }
        else if(startDate.isPresent() && endDate.isPresent()){
            return JsonUtils.getJsonByCampaignByStartAndEndDate(startDate.get(), endDate.get());
        }

        else if(startDate.isPresent()){
            return JsonUtils.getJsonByStartDate(startDate.get());
        }

        else if(endDate.isPresent()){
            return JsonUtils.getJsonByEndDate(endDate.get());
        }

        else if(campaignName.isPresent()){
            return JsonUtils.getJsonByCampaign(campaignName.get());
        }
        return "";
   }
}
