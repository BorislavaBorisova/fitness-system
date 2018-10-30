package commandfactory;

public enum CommandType {
    SIGN_UP() {
        @Override
        Command getCommand() {
            return new SignUpCommand();
        }
    },

    SIGN_IN() {
        @Override
        Command getCommand() {
            return new SignInCommand();
        }
    },

    DELETE_MY_PROFILE() {
        @Override
        Command getCommand() {
            return new DeleteMyProfileCommand();
        }
    },

    SHOW() {
        @Override
        Command getCommand() {
            return new ShowCommand();
        }
    },

    WRITE_MESSAGE() {
        @Override
        Command getCommand() {
            return new WriteMessageCommand();
        }
    },

    SEE_ALL_MESSAGES() {
        @Override
        Command getCommand() {
            return new SeeAllMessagesCommand();
        }
    },

    LOGOUT() {
        @Override
        Command getCommand() {
            return new LogoutCommand();
        }
    },

    VISITS() {
        @Override
        Command getCommand() {
            return new VisitCommand();
        }
    },

    ADD_EQUIPMENT() {
        @Override
        Command getCommand() {
            return new AddEquipmentCommand();
        }
    },

    ACTIVATE_CARD() {
        @Override
        Command getCommand() {
            return new ActivateCardCommand();
        }
    },

    SHOW_STATISTICS() {
        @Override
        Command getCommand() {
            return new ShowStatisticsCommand();
        }
    };

    abstract Command getCommand();
}
