package com.example.assignment_j1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class USDRate {

    @JsonProperty("broj_tecajnice")
    private String brojTecajnice;

    @JsonProperty("datum_primjene")
    private String datumPrimjene;

    @JsonProperty("drzava")
    private String drzava;

    @JsonProperty("drzava_iso")
    private String drzavaIso;

    @JsonProperty("sifra_valute")
    private String sifraValute;

    @JsonProperty("valuta")
    private String valuta;

    @JsonProperty("kupovni_tecaj")
    private String kupovniTecaj;

    @JsonProperty("srednji_tecaj")
    private String srednjiTecaj;

    @JsonProperty("prodajni_tecaj")
    private String prodajniTecaj;

    public String getBrojTecajnice() {
        return brojTecajnice;
    }

    public void setBrojTecajnice(String brojTecajnice) {
        this.brojTecajnice = brojTecajnice;
    }

    public String getDatumPrimjene() {
        return datumPrimjene;
    }

    public void setDatumPrimjene(String datumPrimjene) {
        this.datumPrimjene = datumPrimjene;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getDrzavaIso() {
        return drzavaIso;
    }

    public void setDrzavaIso(String drzavaIso) {
        this.drzavaIso = drzavaIso;
    }

    public String getSifraValute() {
        return sifraValute;
    }

    public void setSifraValute(String sifraValute) {
        this.sifraValute = sifraValute;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getKupovniTecaj() {
        return kupovniTecaj;
    }

    public void setKupovniTecaj(String kupovniTecaj) {
        this.kupovniTecaj = kupovniTecaj;
    }

    public String getSrednjiTecaj() {
        return srednjiTecaj;
    }

    public void setSrednjiTecaj(String srednjiTecaj) {
        this.srednjiTecaj = srednjiTecaj;
    }

    public String getProdajniTecaj() {
        return prodajniTecaj;
    }

    public void setProdajniTecaj(String prodajniTecaj) {
        this.prodajniTecaj = prodajniTecaj;
    }
}
