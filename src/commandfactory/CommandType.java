package commandfactory;

public enum CommandType {
    SignUp() {
        @Override
        Command getCommand() {
            return new SignUpCommand();
        }
    },

    SignIn() {
        @Override
        Command getCommand() {
            return new SignInCommand();
        }
    },

    DeleteMyProfile() {
        @Override
        Command getCommand() {
            return new DeleteMyProfileCommand();
        }
    },

    Show() {
        @Override
        Command getCommand() {
            return new ShowCommand();
        }
    },

    WriteMessage() {
        @Override
        Command getCommand() {
            return new WriteMessageCommand();
        }
    },

    SeeAllMessages() {
        @Override
        Command getCommand() {
            return new SeeAllMessagesCommand();
        }
    },

    Logout() {
        @Override
        Command getCommand() {
            return new LogoutCommand();
        }
    };

    abstract Command getCommand();
}
