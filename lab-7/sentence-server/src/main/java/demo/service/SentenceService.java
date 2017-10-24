package demo.service;

import rx.Observable;
import rx.Single;

/**
 * Describes a demo.service that will build a sentence, somehow, some way.
 */
public interface SentenceService {

	public Single<String> buildSentence();

}
