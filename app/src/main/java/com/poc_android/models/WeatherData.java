package com.poc_android.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class WeatherData implements Parcelable{
    private Coord coord;
    private int cod;
    private String base;
    private String name;
    private Main main;
    private List<Weather> weather;
    private Sys sys;

    public WeatherData(Coord coord, int code, String base, String name, Main main) {
        this.coord = coord;
        this.cod = code;
        this.base = base;
        this.name = name;
        this.main = main;
    }

    public WeatherData(Parcel in) {
        this.coord =in.readParcelable(Coord.class.getClassLoader());
        this.cod = in.readInt();
        this.base = in.readString();
        this.name = in.readString();
        this.main = in.readParcelable(Coord.class.getClassLoader());
    }


    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    public int getCod() {
        return cod;
    }

    public void setCod(int code) {
        this.cod = code;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cod);
        dest.writeParcelable(coord,flags);
        dest.writeString(base);
        dest.writeString(name);
        dest.writeParcelable(main, flags);
    }

    class Main implements Parcelable{
        double temp;
        double pressure;
        int humidity;

        public Main(double temp, int pressure, int humidity) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        protected Main(Parcel in) {
            temp = in.readDouble();
            pressure = in.readDouble();
            humidity = in.readInt();
        }

        public final Creator<Main> CREATOR = new Creator<Main>() {
            @Override
            public Main createFromParcel(Parcel in) {
                return new Main(in);
            }

            @Override
            public Main[] newArray(int size) {
                return new Main[size];
            }
        };

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(temp);
            dest.writeDouble(pressure);
            dest.writeInt(humidity);
        }
    }

    class Coord implements Parcelable{
        double lat;
        double lon;

        public Coord(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        protected Coord(Parcel in) {
            lat = in.readDouble();
            lon = in.readDouble();
        }

        public final Creator<Coord> CREATOR = new Creator<Coord>() {
            @Override
            public Coord createFromParcel(Parcel in) {
                return new Coord(in);
            }

            @Override
            public Coord[] newArray(int size) {
                return new Coord[size];
            }
        };

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(lat);
            dest.writeDouble(lon);
        }
    }

    class Weather implements Parcelable{
        private int id;
        String main;
        String description;
        String icon;

        public Weather() {
        }

        public Weather(int id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        protected Weather(Parcel in) {
            id = in.readInt();
            main = in.readString();
            description = in.readString();
            icon = in.readString();
        }

        public final Creator<Weather> CREATOR = new Creator<Weather>() {
            @Override
            public Weather createFromParcel(Parcel in) {
                return new Weather(in);
            }

            @Override
            public Weather[] newArray(int size) {
                return new Weather[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(main);
            dest.writeString(description);
            dest.writeString(icon);
        }
    }

    class Sys {
        int type;
        int id;
        String country;
        long sunrise;
        long sunset;

        public Sys() {
        }

        public Sys(int type, int id, String country, long sunrise, long sunset) {
            this.type = type;
            this.id = id;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }
    }

    // Accesor methods
    public double getLat() {
        return coord.getLat();
    }

    public double getLon() {
        return coord.getLon();
    }

    public double getPressure() {
        return main.getPressure();
    }

    public double getTemp() {
        return main.getTemp();
    }

    public String getCountry() {
        return sys.getCountry();
    }

    public long getSunrise() {
        return sys.getSunrise();
    }

    public long getSunset() {
        return sys.getSunset();
    }
}