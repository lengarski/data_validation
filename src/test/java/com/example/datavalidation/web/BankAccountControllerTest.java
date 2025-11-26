package com.example.datavalidation.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsBankAccountWhenPayloadIsValid() throws Exception {
        String payload = """
                {
                  \"accountHolder\": \"Alice Example\",
                  \"countryIso2\": \"GB\",
                  \"currencyCode\": \"GBP\",
                  \"iban\": \"GB33NWBK00000000000000\",
                  \"bic\": \"NWBKGB2L\",
                  \"swift\": \"DEUTDEFF500\",
                  \"sortCode\": \"112233\"
                }
                """;

        mockMvc.perform(post("/api/bank-accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.currency").value("GBP"))
                .andExpect(jsonPath("$.countryIso2").value("GB"));
    }

    @Test
    void rejectsBankAccountWhenIbanDoesNotMatchCountry() throws Exception {
        String payload = """
                {
                  \"accountHolder\": \"Bob Example\",
                  \"countryIso2\": \"DE\",
                  \"currencyCode\": \"EUR\",
                  \"iban\": \"GB11NWBK00000000000000\",
                  \"bic\": \"DEUTDEFF\"
                }
                """;

        mockMvc.perform(post("/api/bank-accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.iban").value("IBAN is not valid for the supplied country"));
    }

    @Test
    void rejectsWhenBicIsNotStored() throws Exception {
        String payload = """
                {
                  \"accountHolder\": \"Cara Example\",
                  \"countryIso2\": \"GB\",
                  \"currencyCode\": \"GBP\",
                  \"iban\": \"GB33NWBK00000000000000\",
                  \"bic\": \"UNKNUS33\"
                }
                """;

        mockMvc.perform(post("/api/bank-accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.bic").value("Invalid or unsupported BIC code"));
    }

    @Test
    void rejectsInvalidSortCode() throws Exception {
        String payload = """
                {
                  \"accountHolder\": \"Dana Example\",
                  \"countryIso2\": \"GB\",
                  \"currencyCode\": \"GBP\",
                  \"iban\": \"GB33NWBK00000000000000\",
                  \"bic\": \"NWBKGB2L\",
                  \"sortCode\": \"12345\"
                }
                """;

        mockMvc.perform(post("/api/bank-accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.sortCode").value("Invalid sort code"));
    }
}
