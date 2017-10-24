package demo.service;

import rx.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import demo.dao.AdjectiveClient;
import demo.dao.ArticleClient;
import demo.dao.NounClient;
import demo.dao.SubjectClient;
import demo.dao.VerbClient;
import demo.domain.Word;

@Service
public class WordServiceImpl implements WordService {

	@Autowired VerbClient verbClient;
	@Autowired SubjectClient subjectClient;
	@Autowired ArticleClient articleClient;
	@Autowired AdjectiveClient adjectiveClient;
	@Autowired NounClient nounClient;
	
	
	@Override
	@HystrixCommand(fallbackMethod="getSubjectFallback")
	public Observable<Word> getSubject() {
		return Observable.just(subjectClient.getWord());
	}
	
	public Observable<Word> fallback() {
		return Observable.just(new Word(""));
	}
	
	public Observable<Word> getSubjectFallback() {
		return Observable.just(new Word("Someone"));
	}
	
	public Observable<Word> getNounFallback() {
		return Observable.just(new Word("something"));
	}
	
	
	@Override
	@HystrixCommand(fallbackMethod="fallback")
	public Observable<Word> getVerb() {
		return Observable.just(verbClient.getWord());
	}
	
	@Override
	@HystrixCommand(fallbackMethod="fallback")
	public Observable<Word> getArticle() {
		return Observable.just(articleClient.getWord());
	}
	
	@Override
	@HystrixCommand(fallbackMethod="fallback")
	public Observable<Word> getAdjective() {
		return Observable.just(adjectiveClient.getWord());
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getNounFallback")
	public Observable<Word> getNoun() {
		return Observable.just(nounClient.getWord());
	}	
}
