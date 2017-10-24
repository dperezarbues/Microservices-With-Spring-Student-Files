package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.service.SentenceService;
import rx.Observable;
import rx.Single;



@Controller
public class SentenceController {

	@Autowired SentenceService sentenceService;
	
	
	/**
	 * Display a small list of Sentences to the caller:
	 */
	@GetMapping("/sentence")
	public @ResponseBody Single<String> getSentence() {
		long start = System.currentTimeMillis();
		Observable<Long> now = Observable.defer(()->Observable.just(System.currentTimeMillis()));
		/*String output = 
			"<h3>Some Sentences</h3><br/>" +	  
			sentenceService.buildSentence() + "<br/><br/>" +
			sentenceService.buildSentence() + "<br/><br/>" +
			sentenceService.buildSentence() + "<br/><br/>" +
			sentenceService.buildSentence() + "<br/><br/>" +
			sentenceService.buildSentence() + "<br/><br/>"
			;*/
		long end = System.currentTimeMillis();
		//return output + "Elapsed time (ms): " + (end - start);
		
		return Single.merge(sentenceService.buildSentence(), 
				sentenceService.buildSentence(), 
				sentenceService.buildSentence(),
				sentenceService.buildSentence(),
				sentenceService.buildSentence())
	.startWith("<h3>Some Sentences</h3>").concatWith(Observable.just("Elapsed time (ms): " + (end - start))).reduce((t1,t2)-> t1+"<br/>"+t2).toSingle();
	 
		
	}

}
