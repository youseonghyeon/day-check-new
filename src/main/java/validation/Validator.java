package validation;

import okhttp3.Response;

@FunctionalInterface
public interface Validator {

    boolean proceed(Response body);

}
