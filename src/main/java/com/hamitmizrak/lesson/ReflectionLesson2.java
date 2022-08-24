package com.hamitmizrak.lesson;import java.lang.reflect.Constructor;import java.lang.reflect.Field;import java.lang.reflect.Method;import java.lang.reflect.Modifier;public class ReflectionLesson2 {    public final static int IL_PLAKA=44;    private String cityName;    private int cityCode;    //parametresiz constructor    public ReflectionLesson2() {        this("Malatya",44);    }    //parametreli constructor    public ReflectionLesson2(String cityName, int cityCode) {        this.cityName = cityName;        this.cityCode = cityCode;    }    //getter and setter    public String getCityName() {        return cityName;    }    public ReflectionLesson2 setCityName(String cityName) {        this.cityName = cityName;        return this;    }    public int getCityCode() {        return cityCode;    }    public ReflectionLesson2 setCityCode(int cityCode) {        this.cityCode = cityCode;        return this;    }    //method    public static void cityFull(){        System.out.println("plaka"+IL_PLAKA);    };    @Override    public String toString() {        return "ReflectionLesson2{" +                "cityName='" + cityName + '\'' +                ", cityCode='" + cityCode + '\'' +                '}';    }    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {        try {            //Reflection: bug veya debuglarda, test yapaıalrda kulalnılıyor.            //? Willcard demektir. yani herhangi bir şey gelebilri demektir.            Class<?> lesson2=Class.forName("com.hamitmizrak.lesson.ReflectionLesson2");            //Bütün alanları yazdırmak            //dikkat burada private alanlarınıda alabiliyorum.            for(Field fields: lesson2.getDeclaredFields()) {                System.out.println(" Field "+fields.getName());            }            //Object object=lesson2.newInstance();            Object object=lesson2.getDeclaredConstructor().newInstance();            //parametreli constructor            //eğer çok tane parametre olsaydı varargs ile yazılır  yani ==> String...            //Varargs...            Constructor<?> constructor=object.getClass().getDeclaredConstructor(String.class,Integer.TYPE);            System.out.println("**** Constructor Modifiers ****");            //System.out.println("Modifers: "+constructor.getModifiers());           int modifierInt=constructor.getModifiers();           System.out.println(" Constructor erişim belirleyici Modifier: "+ Modifier.toString(modifierInt));            System.out.println("**** Genel Modifier ***");            System.out.println("Name: "+constructor.getName());            System.out.println("isAccessible:  "+constructor.isAccessible());            System.out.println("isSynthetic:  "+constructor.isSynthetic());            System.out.println("isVarArgs ... : "+constructor.isVarArgs());            System.out.println("**** Metotları değer vermek değiştirmek  ****");            //getCityCode eriştim            Method setCityCodeVariable=lesson2.getMethod("setCityCode",Integer.TYPE);            setCityCodeVariable.invoke(object,44);            Method getCityCodeVariable=lesson2.getMethod("getCityCode");            Object city=getCityCodeVariable.invoke(object);            System.out.println("İl Kodu: "+city);            System.out.println("**** Metotları diğer özelliklerine erişmek örneğin ToStirng erişmek****");            Method toStringMethod=lesson2.getMethod("toString");            Object classDataVariable=toStringMethod.invoke(object);            System.out.println("Data: "+classDataVariable);        }catch (Exception exception){        }    }}