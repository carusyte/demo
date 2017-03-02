This demo is for reproducing the google.Reflections getMethodUsage issue.

When the scanner is scanning for method usages, if there is an anonymous class invokes method defined in the outer class, a org.reflections.ReflectionsException will be thrown. Detailed message: Can't resolve member named 000 for class my.test.scan.TestTarget.access

[http://stackoverflow.com/questions/42528609/org-reflections-reflectionsexception-cant-resolve-member-named-for-class](http://stackoverflow.com/questions/42528609/org-reflections-reflectionsexception-cant-resolve-member-named-for-class)