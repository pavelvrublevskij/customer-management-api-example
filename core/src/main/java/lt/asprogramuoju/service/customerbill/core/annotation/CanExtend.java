package lt.asprogramuoju.service.customerbill.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class with this annotation could be extendable for business purpose.
 * Use that annotation as class marker which would say for developers that class could be extendable or are not full
 * implemented.
 *
 * <p>CanExtend annotation means that usage class itself could has not full implementation or could be improved by other classes.</p>
 *
 * @author pavel.vrublevskij
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@Inherited
@Documented
public @interface CanExtend {
}
