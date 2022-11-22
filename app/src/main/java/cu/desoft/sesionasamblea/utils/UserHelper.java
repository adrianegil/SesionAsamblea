package cu.desoft.sesionasamblea.utils;

import android.content.Context;
import android.content.SharedPreferences;

//CLASE DE AYUDA PARA LA GESTIÓN DE TOKENS Y ALGUNOS DATOS DEL USUARIO
public class UserHelper {

    public static void saveDeputyRegister(Long register, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("autenticacion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("register", register);
        editor.apply();
    }

    public static Long getDeputyRegister(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("autenticacion", Context.MODE_PRIVATE);
        return preferences.getLong("register", 0);
    }

    public static boolean isFirstLaunch(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("autenticacion", Context.MODE_PRIVATE);
        return preferences.getBoolean("firstLaunch", true);
    }

    public static void changefirstLaunch(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("autenticacion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstLaunch", false);
        editor.apply();
    }

    public static void saveToken(String token, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("autenticacion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("autenticacion", Context.MODE_PRIVATE);
        return preferences.getString("token", "");
    }
}
