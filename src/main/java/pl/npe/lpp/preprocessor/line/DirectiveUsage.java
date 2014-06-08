package pl.npe.lpp.preprocessor.line;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.06.14
 * Time: 15:01
 */
public class DirectiveUsage {

    private String directive;

    private List<String> params;

    public DirectiveUsage(String directive, List<String> params) {
        this.directive = directive;
        this.params = params;
    }

    public String getDirective() {
        return directive;
    }

    public List<String> getParams() {
        return Collections.unmodifiableList(params);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectiveUsage usage = (DirectiveUsage) o;

        if (directive != null ? !directive.equals(usage.directive) : usage.directive != null) return false;
        if (params != null ? !params.equals(usage.params) : usage.params != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = directive != null ? directive.hashCode() : 0;
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DirectiveUsage{");
        sb.append("directive='").append(directive).append('\'');
        sb.append(", params=").append(params);
        sb.append('}');
        return sb.toString();
    }
}
