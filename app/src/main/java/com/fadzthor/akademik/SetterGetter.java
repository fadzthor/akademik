package com.fadzthor.akademik;

public class SetterGetter {
    private static String JadwalKuliah, Profil, username, KRS, prodi, semester, jenjang, nama;

    public static String getJadwalKuliah(){return JadwalKuliah;}
    public static String getUsername() {
        return username;
    }
    public static String getProfil() {return Profil;}
    public static String getKRS() {return KRS;}
    public static String getNama() {return nama;}
    public static String getJenjang() {return jenjang;}
    public static String getProdi() {
        return prodi;
    }
    public static String getSemester() {
        return semester;
    }

    public static void setJadwalKuliah(String jadwalKuliah){JadwalKuliah = jadwalKuliah;}
    public static void setUsername(String username) {SetterGetter.username = username;}
    public static void setProfil(String profil) {SetterGetter.Profil = profil;}
    public static void setKRS(String krs) {SetterGetter.KRS = krs;}
    public static void setNama(String nama) {
        SetterGetter.nama = nama;
    }
    public static void setJenjang(String jenjang) {
        SetterGetter.jenjang = jenjang;
    }
    public static void setProdi(String prodi) {
        SetterGetter.prodi = prodi;
    }
    public static void setSemester(String semester) {
        SetterGetter.semester = semester;
    }

    public void SetterGetter(String jadwalKuliah, String profil){
        JadwalKuliah = jadwalKuliah;
        Profil = profil;
    }

//    public void SetterGetter(String profil){profil = profil;}
}
