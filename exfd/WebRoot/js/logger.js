window.EFINDER = window.EFINDER || {};

(function($, EFINDER, undefined) {
    'use strict';

    EFINDER.logger = {
       LEVEL_TRACE  : 0,
       LEVEL_DEBUG  : 1,
       LEVEL_INFO   : 2,
       LEVEL_WARNING: 3,
       LEVEL_ERROR  : 4,

       LEVEL_DESC   : ["Trace", "Debug", "Info", "Warning", "Error"],

       // Default log level is set to DEBUG.
       LOG_LEVEL    : 1,

       logAppender  : null,

       logFunction  : null,

       trace: function(args) {
          this.log(args, this.LEVEL_TRACE);
       },
       debug: function(args) {
          this.log(args, this.LEVEL_DEBUG);
       },
       info: function(args) {
          this.log(args, this.LEVEL_INFO);
       },
       warning: function(args) {
          this.log(args, this.LEVEL_WARNING);
       },
       error: function(args) {
          this.log(args, this.LEVEL_ERROR);
       },

       log: function(args, level) {
          var logTime,
              logString,
              callerDetail = {},
              functionName,
              fileName,
              lineNum;

          if (level >= this.LOG_LEVEL) {
             if (args) {
                logTime = new Date();
                callerDetail = getCallerDetail();
                functionName = !!callerDetail.functionName ? callerDetail.functionName : "";
                fileName = !!callerDetail.fileName ? callerDetail.fileName : "";
                lineNum = !!callerDetail.lineNum ? callerDetail.lineNum : "";
                logString = "[" + logTime.getFullYear() + "-" +
                   pad(logTime.getMonth() + 1, 2) + "-" + pad(logTime.getDate(), 2) + "T" +
                   pad(logTime.getHours(), 2) + ":" + pad(logTime.getMinutes(), 2) + ":" +
                   pad(logTime.getSeconds(), 2) + "." + pad(logTime.getMilliseconds(), 3) +
                   "][" + this.LEVEL_DESC[level] + "]";
                if (!((functionName === "") && (fileName === "") && (lineNum === ""))) {
                   logString += "[" + functionName + " " + fileName + ":" + lineNum + "]";
                }
                logString += ": " + args;
             }

             if (this.logAppender) {
                $(this.logAppender).append(logString + "<br />");
             }
             if (typeof(this.logFunction) === "function") {
                this.logFunction(logString);
             }
             if (!!window.console && (typeof window.console.log === "function")) {
                window.console.log(logString);
             }
          }
       },

       /**
        * Set the log level.
        *
        * @param logLevel [in] the log level of the application.
        */
       setLogLevel: function(logLevel) {
          this.LOG_LEVEL = logLevel;
       },

       /**
        * Set the log appender.
        *
        * @param logAppender [in] a div object to show the logs.
        */
       setLogAppender: function(logAppender) {
          this.logAppender = logAppender;
       },

       /**
        * Set the custom log function.
        *
        * @param logFn [in] a function used to log.
        */
       setLogFunction: function (logFn) {
          this.logFunction = logFn;
       }
    };
}(window.jQuery, window.EFINDER));