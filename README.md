# ifs25037-pbo2026-springboot-cashflow

Aplikasi web pencatat keuangan pribadi (Spring Boot + Maven + Vaadin) dengan Clean Architecture.
Transaksi bertipe Pemasukan atau Pengeluaran; saldo = total pemasukan - total pengeluaran.
Data disimpan di memori (hilang saat aplikasi di-restart).

## Menjalankan

```
.\mvnw.cmd spring-boot:run
```

Buka `http://localhost:8080` (port diatur lewat `APP_PORT` di `.env`).

Build JAR (wajib profil production Vaadin):

```
.\mvnw.cmd clean package -Pproduction
java -jar target\ifs25037-pbo2026-springboot-cashflow-0.0.1-SNAPSHOT.jar
```

## Struktur layer

```
domain/       Transaction, TransactionType, SortOption, exception, IRepository<T, ID>, ITransactionRepository
usecase/      FinanceUseCase (tambah, ubah, hapus, cari, urutkan, saldo) - tanpa Vaadin / System.out
adapter/      InMemoryRepository<T, ID>, TransactionRepository, FinancePresenter
framework/    FinanceView (Vaadin)
config/       AppConfig (composition root, @Bean)
```

Aturan dependensi: domain <- usecase <- adapter / framework.

## Pengujian

```
.\mvnw.cmd clean verify
```

Menjalankan semua test lalu memeriksa coverage Jacoco (instruction/branch/line/method/class = 100%).
Laporan: `target/site/jacoco/index.html`.

```
src/test/.../AppTest.java            smoke test @SpringBootTest + App.main
src/test/.../DotEnvTest.java         pemuat .env
src/test/.../domain/                 Transaction, SortOption, exception
src/test/.../usecase/                FinanceUseCase (validasi, cari, urut, saldo)
src/test/.../adapter/                TransactionRepository, FinancePresenter
src/test/.../config/                 AppConfig
src/test/.../framework/view/         FinanceView (Karibu Testing)
```
