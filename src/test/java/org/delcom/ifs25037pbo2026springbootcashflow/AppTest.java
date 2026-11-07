package org.delcom.ifs25037pbo2026springbootcashflow;

import org.delcom.ifs25037pbo2026springbootcashflow.adapter.presenter.FinancePresenter;
import org.delcom.ifs25037pbo2026springbootcashflow.domain.repository.ITransactionRepository;
import org.delcom.ifs25037pbo2026springbootcashflow.usecase.FinanceUseCase;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Meta;
import com.vaadin.flow.router.PageTitle;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class AppTest {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private FinanceUseCase financeUseCase;

    @Autowired
    private FinancePresenter financePresenter;

    @Test
    void contextLoadsAndWiresCleanArchitectureBeans() {
        assertNotNull(transactionRepository);
        assertNotNull(financeUseCase);
        assertNotNull(financePresenter);
    }

    @Test
    void appShellDeclaresTitleDescriptionAndAuraTheme() {
        assertInstanceOf(AppShellConfigurator.class, new App());
        assertEquals("Cashflow - Catatan Keuangan", App.class.getAnnotation(PageTitle.class).value());
        assertEquals("aura/aura.css", App.class.getAnnotation(StyleSheet.class).value());

        Meta description = Arrays.stream(App.class.getAnnotationsByType(Meta.class))
                .filter(meta -> meta.name().equals("description"))
                .findFirst()
                .orElseThrow();
        assertFalse(description.content().isBlank());
    }

    @Test
    void mainDelegatesToSpringApplicationRun() throws Exception {
        ConfigurableApplicationContext context = mock(ConfigurableApplicationContext.class);
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
            mocked.when(() -> SpringApplication.run(eq(App.class), any(String[].class)))
                    .thenReturn(context);

            App.main(new String[]{"--server.port=0"});

            mocked.verify(() -> SpringApplication.run(eq(App.class), any(String[].class)));
        }
    }
}
