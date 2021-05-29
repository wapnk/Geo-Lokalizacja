package rest.geolocalization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rest.geolocalization.models.Geo;
import rest.geolocalization.services.GeoService;
import javax.validation.Valid;
import java.io.IOException;

@org.springframework.stereotype.Controller
@RequestMapping("/*")
public class GeoController
{
    @Autowired
    private GeoService geoService;

    @GetMapping("")
    public String frontPanel()
    {
        return "frontPanel";
    }
    @GetMapping("new-get")
    public String newLocalization(Model model) throws IOException
    {
        Geo suggested=GeoService.newSuggestedGeo();

        model.addAttribute("suggested",suggested);
        model.addAttribute("geo",new Geo());

        return "localization";
    }
    @PostMapping("new-post")
    public String addLocalization(@ModelAttribute("geo") @Valid Geo geo, BindingResult errorsResult, Model model) throws IOException
    {
        if(errorsResult.hasErrors())
        {
            System.out.println("formularz zawiera błędy");
            errorsResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));

            model.addAttribute("suggested",GeoService.newSuggestedGeo());
            return "localization";
        }
        else
        {
            geoService.saveGeo(geo);
            return "redirect:/frontPanel";
        }
    }
    @GetMapping("history")
    public String history()
    {
        return "historyPanel";
    }
    @GetMapping("getHistoryById")
    public String getHistoryByIdPanel()
    {
        int id = 3; // todo example id
        return "redirect:/getHistoryById?id="+id;
    }

}
