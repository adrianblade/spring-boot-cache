/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class ComplexClient {

	Logger logger = LoggerFactory.getLogger(ComplexClient.class);

	private static final int LIST_SIZE = 1000000;

	private static List<String> sample_country_code = new ArrayList<>();

	private final CountryRepository countryService;

	private final Random random;

	ComplexClient(CountryRepository countryService) {
		this.countryService = countryService;
		this.random = new Random();
	}

	public Country retrieveCountry() {
		if (sample_country_code.size() <= LIST_SIZE) {
			sample_country_code.add(UUID.randomUUID().toString());
		}
		String randomCode = sample_country_code.get(this.random.nextInt(sample_country_code.size()));
		long tm = System.nanoTime();
		Country country = this.countryService.findByCode(randomCode);
		tm = System.nanoTime() - tm;
		// Get elapsed time in seconds
		// Get elapsed time in seconds
		if (tm > 2000000000) {
			//logger.info("BBDD(): "+randomCode+ " " + tm + " ns " + "Data count: " + sample_country_code.size());
		} else {
			logger.info("CACHED: "+randomCode+ " " + tm + " ns " + "Data count: " + sample_country_code.size());
		}
		return country;
	}
	
	public void overload() {
		for (int i = 0; i < 500; i++) {
			sample_country_code.add(UUID.randomUUID().toString());
		}
	}

}
