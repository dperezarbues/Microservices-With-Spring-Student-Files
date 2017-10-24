package demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;
import rx.Single;

/**
 * Build a sentence by assembling randomly generated subjects, verbs, 
 * articles, adjectives, and nouns.  The individual parts of speech will 
 * be obtained by calling the various DAOs.
 */
@Service
public class SentenceServiceImpl implements SentenceService {

	@Autowired WordService wordService;
	

	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public Single<String> buildSentence() {
		return Observable.zip(wordService.getSubject().map(w->w.getString()),
				wordService.getVerb().map(w->w.getString()),
				wordService.getArticle().map(w->w.getString()),
				wordService.getAdjective().map(w->w.getString()),
				wordService.getNoun().map(w->w.getString())
								,(t1,t2,t3,t4,t5) -> t1 + " " + t2 + " " + t3 + " " + t4 + " " + t5).toSingle();
				
		/*		wordService.getVerb().getString(),
				wordService.getArticle().getString(),
				wordService.getAdjective().getString(),
				wordService.getNoun().toBlocking().first().getString() )*/
			
	}	
}
