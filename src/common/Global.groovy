package common

/**
 * This class has the functions that will be used by any class (Doesn't need to be inherited.)
 */
class Global {
    private static def script
    private static Map<String, Object> paramsFromJenkinsFile

    @SuppressWarnings(['GroovyStaticMethodNamingConvention', 'GroovyUntypedAccess', 'GroovyResultOfAssignmentUsed'])
    static set(final script = null, final Map params = [:]) {
        Global.script = script
        paramsFromJenkinsFile = params
    }

    @SuppressWarnings('GroovyUntypedAccess')
    static getScript() {
        return script
    }

    @SuppressWarnings(['GroovyVariableCanBeFinal', 'GroovyParameterNamingConvention'])
    static getParamFromJenkinsFile() {
        return paramsFromJenkinsFile
    }

    @SuppressWarnings(['GroovyVariableCanBeFinal', 'GroovyParameterNamingConvention'])
    static getParamFromJenkinsFile(String key) {
        return paramsFromJenkinsFile[key]
    }

    @SuppressWarnings(['GroovyParameterNamingConvention', 'GroovyUntypedAccess'])
    static getParamFromJenkinsUI(final String key) {
        return script.params[key]
    }

    @SuppressWarnings('GroovyUntypedAccess')
    static def getEnv() {
        return script.env
    }

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyParameterNamingConvention'])
    static def getEnv(final String key) {
        return script.env[key]
    }

}

