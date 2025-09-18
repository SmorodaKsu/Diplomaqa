package ru.netology.test.APITests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.SQLHelper;
import ru.netology.data.ApiHelper;
import ru.netology.data.DataHelper;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.cleanDB;

public class APITests {

    private static List<SQLHelper.PaymentEntity> payments;
    private static List<SQLHelper.CreditRequestEntity> credits;
    private static List<SQLHelper.OrderEntity> orders;
    private static final String paymentUrl = "/payment";
    private static final String creditUrl = "/credit";

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    public static void setUp() {
        cleanDB();
    }

    @AfterEach
    public void tearDown() {
        cleanDB();
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @DisplayName("Действующая карта, запись в таблице payment_entity")
    @Test
    public void shouldValidCardApprovedEntityAdded() {
        var cardInfo = DataHelper.getValidCardApproved();
        ApiHelper.getData(cardInfo, paymentUrl, 200);
        payments = SQLHelper.getPayments();

        assertEquals(1, payments.size());
        assertEquals("APPROVED", payments.get(0).getStatus());
    }


    @DisplayName("Недействующая карта, запись в таблице payment_entity")
    @Test
    public void shouldValidCardDeclinedEntityAdded() {
        var cardInfo = DataHelper.getValidCardDeclined();
        ApiHelper.getData(cardInfo, paymentUrl, 200);
        payments = SQLHelper.getPayments();

        assertEquals(1, payments.size());
        assertEquals("DECLINED", payments.get(0).getStatus());
    }


    @DisplayName("Отправка пустого POST запроса для покупки")
    @Test
    public void shouldPOSTBodyEmpty() {
        var cardInfo = DataHelper.getAllEmpty();
        ApiHelper.getData(cardInfo, paymentUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значеие number пустое для покупки POST запрос")
    @Test
    public void shouldPOSTNumberEmpty() {
        var cardInfo = DataHelper.getCardEmpty();
        ApiHelper.getData(cardInfo, paymentUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значеие month пустое для покупки POST запрос")
    @Test
    public void shouldPOSTMonthEmpty() {
        var cardInfo = DataHelper.getMonthEmpty();
        ApiHelper.getData(cardInfo, paymentUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значение year пустое для покупки POST запрос")
    @Test
    public void shouldPOSTYearEmpty() {
        var cardInfo = DataHelper.getYearEmpty();
        ApiHelper.getData(cardInfo, paymentUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значение holder пустое для покупки POST запрос")
    @Test
    public void shouldPOSTHolderEmpty() {
        var cardInfo = DataHelper.getHolderEmpty();
        ApiHelper.getData(cardInfo, paymentUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значение cvc пустое для покупки POST запрос")
    @Test
    public void shouldPOSTCvcEmpty() {
        var cardInfo = DataHelper.getCvcEmpty();
        ApiHelper.getData(cardInfo, paymentUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Покупка тура в кредит с действующей карты, создание записи в таблице credit_request_entity")
    @Test
    public void shouldValidTestCreditCardApprovedEntityAdded() {
        var cardInfo = DataHelper.getValidCardApproved();
        ApiHelper.getData(cardInfo, creditUrl, 200);
        credits = SQLHelper.getCreditRequests();

        assertEquals(1, credits.size());
        assertEquals("APPROVED", credits.get(0).getStatus());
    }


    @DisplayName("Покупка тура в кредит с недействующей карты, создание записи в таблице credit_request_entity")
    @Test
    public void shouldValidTestCreditCardDeclinedEntityAdded() {
        var cardInfo = DataHelper.getValidCardDeclined();
        ApiHelper.getData(cardInfo, creditUrl, 200);
        credits = SQLHelper.getCreditRequests();

        assertEquals(1, credits.size());
        assertEquals("DECLINED", credits.get(0).getStatus());
    }


    @DisplayName("Отправка пустого POST запроса кредита POST запрос")
    @Test
    public void shouldCreditPOSTBodyEmpty() {
        var cardInfo = DataHelper.getAllEmpty();
        ApiHelper.getData(cardInfo, creditUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значение number пустое для покупки в кредит POST запрос")
    @Test
    public void shouldCreditPOSTNumberEmpty() {
        var cardInfo = DataHelper.getCardEmpty();
        ApiHelper.getData(cardInfo, creditUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значение month пустое для покупки в кредит POST запрос")
    @Test
    public void shouldCreditPOSTMonthEmpty() {
        var cardInfo = DataHelper.getMonthEmpty();
        ApiHelper.getData(cardInfo, creditUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значение year пустое для покупки в кредит POST запрос")
    @Test
    public void shouldCreditPOSTYearEmpty() {
        var cardInfo = DataHelper.getYearEmpty();
        ApiHelper.getData(cardInfo, creditUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значение holder пустое для покупки в кредит POST запрос")
    @Test
    public void shouldCreditPOSTHolderEmpty() {
        var cardInfo = DataHelper.getHolderEmpty();
        ApiHelper.getData(cardInfo, creditUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }


    @DisplayName("Значение cvc пустое для покупки в кредит POST запрос")
    @Test
    public void shouldCreditPOSTCvcEmpty() {
        var cardInfo = DataHelper.getCvcEmpty();
        ApiHelper.getData(cardInfo, creditUrl, 400);
        payments = SQLHelper.getPayments();
        credits = SQLHelper.getCreditRequests();
        orders = SQLHelper.getOrders();

        assertEquals(0, payments.size());
        assertEquals(0, credits.size());
        assertEquals(0, orders.size());
    }

}