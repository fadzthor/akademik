package com.fadzthor.akademik;

public class SetterGetter {
    private static String JadwalKuliah, Profil, username, KRS, UKT;

    public static String getJadwalKuliah(){return JadwalKuliah;}
    public static String getUsername() {
        return username;
    }
    public static String getProfil() {return Profil;}
    public static String getKRS() {return KRS;}
    public static String getUKT() {return UKT;}

    public static void setJadwalKuliah(String jadwalKuliah){JadwalKuliah = jadwalKuliah;}
    public static void setUsername(String username) {SetterGetter.username = username;}
    public static void setProfil(String profil) {SetterGetter.Profil = profil;}
    public static void setKRS(String krs) {SetterGetter.KRS = krs;}
    public static void setUKT(String ukt) {
        SetterGetter.UKT = ukt;
    }

    public void SetterGetter(String jadwalKuliah, String profil){
        JadwalKuliah = jadwalKuliah;
        Profil = profil;
    }

//    public void SetterGetter(String profil){profil = profil;}
}
