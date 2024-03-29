package ru.croc.javaschool.homework4.task1.employment;

/**
 * Сотрудник.
 *
 * @author Danila Fedortsov
 */
public class Employee implements Comparable<Employee> {
    /**
     * Уникальный номер.
     */
    private final Integer id;

    /**
     * Имя.
     */
    private final String name;

    /**
     * Непосредственный начальник.
     */
    private Employee manager;

    /**
     * Создаёт {@link Employee}.
     *
     * @param id   уникальный номер
     * @param name имя
     */
    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает уникальный номер сотрудника.
     *
     * @return уникальный номер
     */
    public Integer getId() {
        return id;
    }

    /**
     * Возвращает имя сотрудника.
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает начальника данного сотрудника. <br>
     * null означает, что данный сотрудник является самым главным в компании.
     *
     * @return начальник, либо null
     */
    public Employee getManager() {
        return manager;
    }

    /**
     * Задаёт начальника для данного работника.
     *
     * @param manager начальник
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }


    /**
     * Реализует toString для объектов класса {@link Employee}.
     *
     * @return строка формата: "{@link #id}#{@link #name}"
     */
    @Override
    public String toString() {
        return id + "#" + name;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param employee the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Employee employee) {
        if (name.compareToIgnoreCase(employee.getName()) == 0) {
            return id.compareTo(employee.getId());
        }
        return name.compareToIgnoreCase(employee.getName());
    }
}
