package com.example.financialapplication.data.network

import javax.inject.Inject

class MockItemsApi @Inject constructor() {

    fun getItemsMock() = ITEMS_MOCK

    companion object {
        private const val ITEMS_MOCK = "[ \n" +
                "\t{\n" +
                "\t\t\"id\": \"34567\",\n" +
                "\t\t\"summary\": \"albérlet és rezsi április\",\n" +
                "\t\t\"category\": \"housing\",\n" +
                "\t\t\"sum\": 175000,\n" +
                "\t\t\"currency\": \"HUF\",\n" +
                "\t\t\"paid\": \"2022-04-20T12:56:00Z\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"34568\",\n" +
                "\t\t\"summary\": \"reggeli szendvics\",\n" +
                "\t\t\"category\": \"food\",\n" +
                "\t\t\"sum\": 750,\n" +
                "\t\t\"currency\": \"HUF\",\n" +
                "\t\t\"paid\": \"2022-04-21T10:21:00Z\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"34569\",\n" +
                "\t\t\"summary\": \"vonaljegy Oktogontól Nyugatiba\",\n" +
                "\t\t\"category\": \"travel\",\n" +
                "\t\t\"sum\": 350,\n" +
                "\t\t\"currency\": \"HUF\",\n" +
                "\t\t\"paid\": \"2022-04-21T10:54:00Z\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"34570\",\n" +
                "\t\t\"summary\": \"vonatjegy haza\",\n" +
                "\t\t\"category\": \"travel\",\n" +
                "\t\t\"sum\": 1250,\n" +
                "\t\t\"currency\": \"HUF\",\n" +
                "\t\t\"paid\": \"2022-04-21T11:16:00Z\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"34571\",\n" +
                "\t\t\"summary\": \"ruha a megnyitóra\",\n" +
                "\t\t\"category\": \"clothing\",\n" +
                "\t\t\"sum\": 12000,\n" +
                "\t\t\"currency\": \"HUF\",\n" +
                "\t\t\"paid\": \"2022-04-22T20:26:00Z\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"34572\",\n" +
                "\t\t\"summary\": \"pizza az Oktogonnál\",\n" +
                "\t\t\"category\": \"food\",\n" +
                "\t\t\"sum\": 3400,\n" +
                "\t\t\"currency\": \"HUF\",\n" +
                "\t\t\"paid\": \"2022-04-23T10:55:00Z\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"34573\",\n" +
                "\t\t\"summary\": \"havi bérlet\",\n" +
                "\t\t\"category\": \"travel\",\n" +
                "\t\t\"sum\": 3450,\n" +
                "\t\t\"currency\": \"HUF\",\n" +
                "\t\t\"paid\": \"2022-04-24T13:28:00Z\"\n" +
                "\t}\n" +
                "]"
    }
}