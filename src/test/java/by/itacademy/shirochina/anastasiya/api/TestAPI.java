package by.itacademy.shirochina.anastasiya.api;

import by.itacademy.shirochina.anastasiya.utils.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestAPI {
    Util util;
    PostObject postObject;

    @BeforeEach
    public void warmUp() {
        util = new Util();
        postObject = new PostObject();
    }

    @Test
    public void testLoginFormWithCorrectData() {
        String htmlResponse = given().formParams(postObject.getFormParamsWithCorrectData()).when().
                post(postObject.endpoint).then().assertThat().statusCode(200).extract().asString();
        Document document = Jsoup.parse(htmlResponse);
        String actual = document.getElementsByTag("span").text();
        Assertions.assertEquals(postObject.successMessage, actual);
    }

    @Test
    public void testLoginFormWithIncorrectData() {
        String htmlResponse = given().formParams(postObject.getFormParamsWithIncorrectData()).when().
                post(postObject.endpoint).then().assertThat().statusCode(200).extract().asString();
        Document document = Jsoup.parse(htmlResponse);
        String actual = document.getElementsByTag("font").text();
        Assertions.assertEquals(postObject.errorMessage, actual);
    }

    @Test
    public void testSearchForValidData() {
        String htmlResponse = given().queryParams(postObject.getQueryParams("t-shirt")).when().
                get(postObject.searchEndpoint).then().assertThat().statusCode(200).extract().body().asString();
        Document document = Jsoup.parse(htmlResponse);
        String actual = document.select("li[data-product-id]").select("input[value]").get(2).attr("value").toString();
        Assertions.assertEquals("Бюстгальтер t-shirt без косточек белого цвета", actual);
    }

    @Test
    public void testSearchForInvalidData() {
        String htmlResponse = given().queryParams(postObject.getQueryParams("lnl/cN?LAScn/lihACb?LBJ")).when().
                get(postObject.searchEndpoint).then().assertThat().statusCode(200).extract().body().asString();
        ;
        Document document = Jsoup.parse(htmlResponse);
        String actual = document.select("div.text").text();
        Assertions.assertEquals("Сожалеем, но по вашему запросу ничего не найдено", actual);
    }
}
