/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Amplang {
    
    
    int id;
    String nama;
    int harga;
    String varian, kategori;
    int stok;

    public Amplang(int id, String nama, int harga, String varian, String kategori, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.varian = varian;
        this.kategori = kategori;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getVarian() {
        return varian;
    }

    public void setVarian(String varian) {
        this.varian = varian;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    
}
