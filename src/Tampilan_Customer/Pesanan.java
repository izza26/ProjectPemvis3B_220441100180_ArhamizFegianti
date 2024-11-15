/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tampilan_Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amira Alissiya
 */
public class Pesanan {
    private int id;
    private String nama;
    private int harga;
    private String jenisMenu; // "Food", "Drink", or "Snack"
    private Integer id_food;
    private Integer id_drink;
    private Integer id_snack;
    private Integer id_transaksi;
    private int jumlah;

    public Pesanan(int id, String nama, int harga, String jenisMenu, int jumlah) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jenisMenu = jenisMenu;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }
    
    public int getTotalHarga() {
        return harga * jumlah;
    }

    public String getJenisMenu() {
        return jenisMenu;
    }
    
    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void id_DB(Connection conn) throws SQLException {
        // Inisialisasi semua ID ke null sebagai default
        id_food = null;
        id_drink = null;
        id_snack = null;

        String sql = "";
        switch (jenisMenu) {
            case "Food":
                sql = "SELECT id_food FROM food WHERE id_food = ?";
                break;
            case "Drink":
                sql = "SELECT id_drink FROM drink WHERE id_drink = ?";
                break;
            case "Snack":
                sql = "SELECT id_snack FROM snack WHERE id_snack = ?";
                break;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // Mengisi ID yang sesuai berdasarkan jenis menu
            if (rs.next()) {
                if (jenisMenu.equals("Food")) {
                    id_food = rs.getInt("id_food");
                } else if (jenisMenu.equals("Drink")) {
                    id_drink = rs.getInt("id_drink");
                } else if (jenisMenu.equals("Snack")) {
                    id_snack = rs.getInt("id_snack");
                }
            }
        }
    }
    
    public void fetchIdTransaksi(Connection conn, int idPegawai, String customerName) throws SQLException {
        String sql = "SELECT id_transaksi FROM transaksi";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPegawai);
            stmt.setString(2, customerName);

            ResultSet rs = stmt.executeQuery();

            // Mengambil id_transaksi jika ada
            if (rs.next()) {
                id_transaksi = rs.getInt("id_transaksi"); // Mengambil nilai id_transaksi
            } else {
                // Tangani jika tidak ditemukan, misalnya dengan memberikan id_transaksi default
                id_transaksi = null; // Atau bisa diberikan nilai default seperti 0 jika diperlukan
            }
        }
    }



    // Getter dan Setter untuk id_transaksi
    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }



    public Integer getId_food() {
        return id_food;
    }

    public Integer getId_drink() {
        return id_drink;
    }

    public Integer getId_snack() {
        return id_snack;
    }
}
