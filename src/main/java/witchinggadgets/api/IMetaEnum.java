package witchinggadgets.api;

public interface IMetaEnum<T extends Enum<T> & IMetaEnum<T>> {

    int getMeta();

    static <T extends Enum<T> & IMetaEnum<T>> T fromMeta(T[] values, int meta) {
        if (meta < 0) return null;
        for (T v : values) {
            if (v.getMeta() == meta) return v;
        }
        return null;
    }

    static <T extends Enum<T> & IMetaEnum<T>> T[] createLookup(T[] values) {
        int max = 0;
        for (T v : values) {
            if (v.getMeta() > max) max = v.getMeta();
        }

        @SuppressWarnings("unchecked")
        T[] lookup = (T[]) java.lang.reflect.Array.newInstance(values.getClass().getComponentType(), max + 1);

        for (T v : values) {
            lookup[v.getMeta()] = v;
        }

        return lookup;
    }

    static <T extends Enum<T> & IMetaEnum<T>> T fromLookup(T[] lookup, int meta) {
        return meta >= 0 && meta < lookup.length ? lookup[meta] : null;
    }
}
