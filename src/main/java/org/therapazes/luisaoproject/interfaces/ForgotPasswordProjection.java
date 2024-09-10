package org.therapazes.luisaoproject.interfaces;

import java.util.Date;

public interface ForgotPasswordProjection {
    String getCode();
    Date getExpirationTime();
}
