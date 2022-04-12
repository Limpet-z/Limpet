package com.limpet1.controllers;

import com.limpet1.model.XUser;
import com.limpet1.repository.BinanceRepository;
import com.limpet1.repository.UserRepositoryJPA;
import com.limpet1.rest.binance.BinanceRestControllerV2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class PortfolioController {

    private static final String PORTFOLIO = "portfolio";
    private final UserRepositoryJPA userRepositoryJPA;
    private final BinanceRepository binanceRepository;
    private final AscendexRepository ascendexRepository;
    private final BinanceRestControllerV2 binanceRestControllerV2;
    private final AscendexRestControllerV3 ascendexRestControllerV3;

    public PortfolioController(UserRepositoryJPA userRepositoryJPA, BinanceRepository binanceRepository, BinanceRestControllerV2 binanceRestControllerV2) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.binanceRepository = binanceRepository;
        this.binanceRestControllerV2 = binanceRestControllerV2;
    }

    @GetMapping("/portfolio")
    public String mainPage(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        XUser xUser = userRepositoryJPA.findByEmail(user.getUsername());
        String email = xUser.getEmail();
        List<String> list = Arrays.asList("Ticker", "Market Price", "Quantity", "NetCost", "Total in USD");
        List<Map<String, Object>> columns = new ArrayList<>();

        /* ---  Exchange rates --- */

        var a = binanceRestControllerV2.coinInfo1(email);
        var b = ascendexRestControllerV3.accountInfo(email);


        Double value = null;
        for (var i : a.entrySet()) {
            double x = currentPrice.averagePriceImpl(i.getKey());
            double total = i.getValue() * x;

            columns.add(Map.of("Ticker", i.getKey(), "Market Price", i.getValue() * x + " $", "Quantity",

                    i.getValue() + "  " + i.getKey(), "NetCost", "0" + " $", "Total in USD",

                    total + " $"));


        }


        for (var i : b.entrySet()) {
            double x = currentPrice.averagePriceImpl(i.getKey());
            double total = i.getValue() * x;

            columns.add(Map.of("Ticker", i.getKey(), "Market Price", i.getValue() * x + " $", "Quantity",

                    i.getValue() + "  " + i.getKey(), "NetCost", "0" + " $", "Total in USD",

                    total + " $"));

        }

        model.addAttribute("value", value);
        model.addAttribute("xUser", xUser);
        model.addAttribute("list", list);
        model.addAttribute("columns", columns);

        return PORTFOLIO;
    }
}
