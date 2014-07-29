package pl.npe.lpp.cmdline;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 04.06.14
 * Time: 18:35
 */
enum LppOption {

    CHARSET("charset") {
        @Override
        @SuppressWarnings("AccessStaticViaInstance")
        public Option buildOption() {
            return OptionBuilder
                    .withArgName("encoding")
                    .hasArg()
                    .withDescription("Character encoding of root file")
                    .create(getOptionName());
        }
    },
    ROOT_FILE("file") {
        @SuppressWarnings("AccessStaticViaInstance")
        @Override
        public Option buildOption() {
            return OptionBuilder
                    .withArgName("file")
                    .hasArg()
                    .isRequired(true)
                    .withDescription("Root file")
                    .create(getOptionName());
        }
    },
    FLAGS("flags") {
        @Override
        @SuppressWarnings("AccessStaticViaInstance")
        Option buildOption() {
            return OptionBuilder
                    .withArgName("flags")
                    .hasArg()
                    .withDescription("Flags to be set at start")
                    .create(getOptionName());
        }
    },
    OUTPUT_FILE("output") {
        @Override
        @SuppressWarnings("AccessStaticViaInstance")
        Option buildOption() {
            return OptionBuilder
                    .withArgName("output")
                    .hasArg()
                    .withDescription("Output file with preprocessed LaTeX content")
                    .create(getOptionName());
        }
    },
    OVERWRITE("overwrite"){
        @Override
        @SuppressWarnings("AccessStaticViaInstance")
        Option buildOption() {
            return OptionBuilder
                    .hasArg(false)
                    .withDescription("Overwrites existing output file")
                    .create(getOptionName());
        }
    };

    LppOption(String optionName) {
        this.optionName = optionName;
    }

    private final String optionName;
    private Option option;

    public String getOptionName() {
        return optionName;
    }

    public Option getOption(){
        if(option == null){
            option = buildOption();
        }
        return option;
    }

    abstract Option buildOption();

    public static Options getOptions(){
        Options options = new Options();
        for(LppOption option : values()){
            options.addOption(option.getOption());
        }
        return options;
    }

}
