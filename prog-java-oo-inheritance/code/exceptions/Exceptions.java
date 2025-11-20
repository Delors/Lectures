class Exceptions {

    static void m1() throws AThrowable{
        throw new AThrowable("AThrowable");
    }

    static void m2() throws AnException {
        throw new AnException("AnException");
    }

    static void m3() {
        throw new AnError("AnError");
    }

    static void m4(){
        throw new ARuntimeException("ARuntimeException");
    }    
}

