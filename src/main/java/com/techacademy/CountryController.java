package com.techacademy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("country")
public class CountryController {

	private final CountryService service;

	public CountryController(CountryService service) {

		this.service = service;

	}


	// -----一覧画面-------

	@GetMapping("/list")

	public String getList(Model model) {

		//全件検索結果をModelに登録

		model.addAttribute("countrylist",service.getCountryList());

		//country/list.htmlに画面遷移

		return "country/list";

	}

	@GetMapping("/detail/{code}")

	public String getdetail(@PathVariable("code")String code, Model model) {

		Country country = service.getCountry(code);

		model.addAttribute("country" ,country);

		return "country/detail";

	}

	@GetMapping("/delete/{code}/")

	public String getdelete(@PathVariable("code") String code, Model model) {

		Country country =service.getCountry(code);

		model.addAttribute("country", country);

		return "country/delete";
	}








 //追加
	//詳細画面

	@GetMapping(value = { "/detail", "/detail/{code}/"})

	public String getCountry(@PathVariable(name ="code", required =false) String code, Model model) {
		//コードが指定されていいたら検索結果、なければ空のクラスを指定
		Country country = code !=null ? service.getCountry(code) : new Country();

		//Modelに登録

		model.addAttribute("country", country);

		//country.detail.htmlに画面遷移

		return "country/detail";

	}


	//更新（追加）

	@PostMapping("/detail")

	public String postCountry(@RequestParam("code") String code, @RequestParam("name") String name,

			@RequestParam("population") int population, Model model) {

		//更新　（追加）

		service.updateCountry(code, name, population);

		//一覧画面にリダイレクト

		return "redirect:/country/list";

	}

	//  削除画面

	@GetMapping("/delete")

	public String deleteCountryForm(Model model) {

		//country/delete.htmlに画面遷移

		return "country/delete";
	}

	//削除

	@PostMapping("/delete")

	public String deleteCountry(@RequestParam("code") String code, Model model) {

		//削除

		service.deleteCountry(code);

		//一覧画面にリダイレクト

		return "redirect:/country/list";


	}

	@GetMapping("/country/delete/{code}")

	public String deletecode(@PathVariable String deletecode, Model model) {

		model.addAttribute("code",deletecode);

		return "country/delete";

	}


	}


