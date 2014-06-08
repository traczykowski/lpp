package pl.npe.lpp.directive;

import pl.npe.lpp.preprocessor.source.ProcessingContext;
import pl.npe.lpp.preprocessor.source.CannotReadFromFileException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 20.05.14
 * Time: 10:37
 */
enum Directive {
    VERBATIM("VERBATIM", new VerbatimExpander()),
    SET_FLAG("SETFLAG", new SetFlagExpander()),
    IF_SET("IFSET", new IfSetExpander()),
    INCLUDE("INCLUDE", new IncludeExpander()),
    ENABLE_REPLACEMENTS("ENABLEREPLACEMENTS", new EnableReplacementsExpander()),
    REPLACE("REPLACE", new ReplaceExpander()),
    RESOLVE_PATHS("RESOLVEPATHS", new ResolvePathsExpander()),
    STOP_INCLUDE("STOPINCLUDE", new StopIncludeExpander()),
    UNSET_FLAG("UNSETFLAG", new UnsetFlagExpander()),
    HTTP_GET_REQUEST("HTTPGETREQUEST", new HttpGetRequestExpander()),
    CONTEXT_DUMP("CONTEXTDUMP", new ContextDumpExpander())

        ;

    Directive(String identifier, Expander expander) {
        this.expander = expander;
        this.identifier = identifier;
    }

    private final Expander expander;
    private final String identifier;

    public String expand(List<String> params, ProcessingContext context) throws ExpanderException, CannotReadFromFileException, UnknownDirectiveException {
        return expander.expand(params, context);
    }

    public static Directive getByIdentifier(String identifier, ProcessingContext context) throws UnknownDirectiveException {      //todo ta metoda mogłą by usuwać znaki _ jeżeli takie by były
        Directive[] values = Directive.values();
        String id = identifier.toUpperCase();
        for(Directive directive : values){
            if(id.equals(directive.identifier)){
                return directive;
            }
        }
        throw new UnknownDirectiveException(context, "Unknown directive identifier: " + identifier);
    }

}
