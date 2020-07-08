package ly.potential.command;

public enum CommandType {
    C("C"){
        @Override
        public int getNumberOfArguments() {
            return 2;
        }
    },
    L("L") {
        @Override
        public int getNumberOfArguments() {
            return 4;
        }
    },
    R("R") {
        @Override
        public int getNumberOfArguments() {
            return 4;
        }
    },
    B("B") {
        @Override
        public int getNumberOfArguments() {
            return 3;
        }
    },
    Q("Q") {
        @Override
        public int getNumberOfArguments() {
            return 0;
        }
    },
    ;

    private final String text;

    CommandType(String text) {
        this.text = text;
    }

    public abstract int getNumberOfArguments();
}
