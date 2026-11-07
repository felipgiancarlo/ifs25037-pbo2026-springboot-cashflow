package org.delcom.ifs25037pbo2026springbootcashflow;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Meta;
import com.vaadin.flow.router.PageTitle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point aplikasi sekaligus "app shell" Vaadin: tempat mengatur isi {@code <head>} halaman
 * yang dikirim pertama kali ke browser (judul, deskripsi, tema), sehingga terbaca mesin pencari
 * dan alat audit tanpa menunggu JavaScript berjalan.
 *
 * <p>Begitu ada app shell, Vaadin tidak lagi memuat tema Aura secara otomatis, jadi tema
 * dipasang eksplisit lewat {@code @StyleSheet} (nilainya sama dengan {@code Aura.STYLESHEET}).
 */
@SpringBootApplication
@StyleSheet("aura/aura.css")
@PageTitle("Cashflow - Catatan Keuangan")
@Meta(name = "description",
        content = "Aplikasi web pencatat keuangan pribadi: catat pemasukan dan pengeluaran, "
                + "cari dan urutkan transaksi, serta pantau saldo secara langsung.")
public class App implements AppShellConfigurator {

    public static void main(String[] args) throws Exception {
        DotEnv.load();
        SpringApplication.run(App.class, args);
    }
}
