/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package exp;

import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.*;

public class ReplaceBenchmark {
    private static class Rule {
        String regex;
        Pattern pattern;
        String replacement;

        Rule(String regex, String replacement) {
            this.regex = regex;
            this.pattern = Pattern.compile(regex);
            this.replacement = replacement;
        }
    }
    private static final Rule[] rules = {
            new Rule("by", "?"),
            new Rule("<p>", ""),
            new Rule("</p>", ""),
            new Rule("[0-9]", "num"),
            new Rule("code", "a")
    };
    @State(Scope.Thread)
    public static class TestData {
        public final String text = "<p>\n"
                + "    Notice how the <code>testMethod()</code> method now returns the <code>sum</code> variable. This way the JVM\n"
                + "    cannot just eliminate the addition, because the return value might be used by the caller. JMH will take of\n"
                + "    tricking the JVM into believing that the return value is actually used.1 2 3\n"
                + "</p>";

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public String replace0(TestData data) {
        String replacedText = data.text;
        for(Rule rule: rules) {
            replacedText = ReplaceUtils.replaceAll0(replacedText, rule.regex, rule.replacement);

        }
        return replacedText;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public String replace1(TestData data) {
        String replacedText = data.text;
        for(Rule rule: rules) {
            replacedText = ReplaceUtils.replaceAll1(rule.pattern, replacedText, rule.replacement);

        }
        return replacedText;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public String replace2(TestData data) {
        String replacedText = data.text;
        for(Rule rule: rules) {
            replacedText = ReplaceUtils.replaceAll2(rule.pattern, replacedText, rule.replacement);

        }
        return replacedText;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public String replace3(TestData data) {
        StringBuffer replacedText = new StringBuffer(data.text);
        for(Rule rule: rules) {
            replacedText = ReplaceUtils.replaceAll3(rule.pattern, replacedText, rule.replacement);

        }
        return replacedText.toString();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public String replace4(TestData data) {
        StringBuilder replacedText = new StringBuilder(data.text);
        for(Rule rule: rules) {
            replacedText = ReplaceUtils.replaceAll4(rule.pattern, replacedText, rule.replacement);

        }
        return replacedText.toString();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public String replace5(TestData data) {
        StringBuilder replacedText = new StringBuilder(data.text);
        for(Rule rule: rules) {
            replacedText = ReplaceUtils.replaceAll5(rule.pattern, replacedText, rule.replacement);

        }
        return replacedText.toString();
    }

}

