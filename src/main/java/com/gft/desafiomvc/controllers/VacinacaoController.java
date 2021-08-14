package com.gft.desafiomvc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.desafiomvc.entities.Vacinacao;
import com.gft.desafiomvc.services.LocalVacinacaoService;
import com.gft.desafiomvc.services.LoteVacinaService;
import com.gft.desafiomvc.services.PessoaService;
import com.gft.desafiomvc.services.VacinacaoService;

@Controller
@RequestMapping("/vacinacao")
public class VacinacaoController {

	@Autowired
	VacinacaoService vacinacaoService;

	@Autowired
	LoteVacinaService loteVacinaService;

	@Autowired
	LocalVacinacaoService localVacinacaoService;

	@Autowired
	PessoaService pessoaService;

	@RequestMapping(method = RequestMethod.GET, path = "/novo")
	public ModelAndView criarVacinacao() {

		ModelAndView mv = new ModelAndView("vacinacao/form.html");
		mv.addObject("vacinacao", new Vacinacao());
		mv.addObject("listalote", loteVacinaService.listarLoteVacina());
		mv.addObject("listalocal", localVacinacaoService.listarLocalVacinacao());
		mv.addObject("listapessoa", pessoaService.listarPessoas());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/novo")
	public ModelAndView salvarVacinacao(@Valid Vacinacao vacinacao, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("vacinacao/form.html");

		boolean novaVacinacao = true;

		if (vacinacao.getId() != null) {
			novaVacinacao = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("vacinacao", vacinacao);
			return mv;
		}

		Vacinacao vacinacaoSalva = vacinacaoService.salvarVacinacao(vacinacao);

		if (novaVacinacao) {
			mv.addObject("vacinacao", new Vacinacao());
		} else {
			mv.addObject("vacinacao", vacinacaoSalva);
		}

		mv.addObject("mensagem", "Successo: vacinação salva!");

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cadastrarVacinacao(String cpf) {

		ModelAndView mv = new ModelAndView("vacinacao/list.html");

		if (cpf != null) {
			mv.addObject("lista", vacinacaoService.listarVacinacaoPessoaId(cpf));
		} else {
			mv.addObject("lista", vacinacaoService.listarVacinacao());
		}

		return mv;
		
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET) public ModelAndView
	 * listarVacinacao() {
	 * 
	 * ModelAndView mv = new ModelAndView("vacinacao/list.html");
	 * mv.addObject("lista", vacinacaoService.listarVacinacao());
	 * 
	 * return mv; }
	 */

	@RequestMapping(path = "/edit")
	public ModelAndView editarVacinacao(@RequestParam Long id) {

		ModelAndView mv = new ModelAndView("vacinacao/form.html");

		try {
			Vacinacao vacinacao = vacinacaoService.encontrarVacinacao(id);
			mv.addObject("vacinacao", vacinacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.addObject("lista", vacinacaoService.listarVacinacao());
		mv.addObject("listalote", loteVacinaService.listarLoteVacina());
		mv.addObject("listalocal", localVacinacaoService.listarLocalVacinacao());
		mv.addObject("listapessoa", pessoaService.listarPessoas());

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/delete")
	public ModelAndView deletarVacinacao(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/vacinacao");

		try {
			vacinacaoService.excluirVacinacao(id);
			redirectAttributes.addFlashAttribute("mensagem", "Successo: vacinação excluída!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir vacinação.");
		}

		return mv;
	}
}
