package rest.geolocalization.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.geolocalization.models.Geo;
import rest.geolocalization.repositories.GeoRepository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
public class GeoService
{
    @Autowired
    private GeoRepository geoRepository;
    private static Geo suggestedGeo;

    public List<Geo> getGeoList()
    {
        return geoRepository.getGeoList();
    }
    public Geo getGeoById(int id)
    {
        return geoRepository.getGeoById(id);
    }
    public Geo saveGeo(Geo geo)
    {
        if(geo.getDate()==null) geo.setDate(suggestedGeo.getDate());
        if(geo.getCity()==null) geo.setCity(suggestedGeo.getCity());
        if(geo.getIp()==null) geo.setIp(suggestedGeo.getIp());

        geoRepository.save(geo);
        return geo;
    }
    public Geo saveSuggestedGeo() throws IOException
    {
        Geo geo=newSuggestedGeo();
        return geoRepository.save(geo);
    }
    public static Geo receiveSuggestedGeo() throws IOException
    {
        if(suggestedGeo!=null) return suggestedGeo;
        else return newSuggestedGeo();
    }

    public static Geo newSuggestedGeo() throws IOException
    {
        URL url=new URL("https://freegeoip.app/json");
        InputStreamReader jsonObiect=new InputStreamReader(url.openStream());
        Geo geo=new Gson().fromJson(jsonObiect, Geo.class);
        geo.setDate(new Date());
        suggestedGeo=geo;
        return geo;
    }
}
