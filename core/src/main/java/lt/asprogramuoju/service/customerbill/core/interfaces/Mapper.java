package lt.asprogramuoju.service.customerbill.core.interfaces;

/**
 * Mapper class
 * <p/>
 * Map from class {@link E} to class {@link R}
 *
 * @param <E> - input class which will be mapped
 * @param <R> - result class
 * @author Pavel Vrublevskij
 */
@FunctionalInterface
public interface Mapper<E, R> {

    R mapToDomain(E data);
}
