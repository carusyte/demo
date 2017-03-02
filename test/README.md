This demo is for reproducing the google.Reflections getMethodUsage issue.

When you call getMethodUsage() on a private method, if there is an anonymous inner class which invokes this private method, an org.reflections.ReflectionsException will be thrown. Detailed message: Can't resolve member named 000 for class my.test.scan.TestTarget.access

Just run maven test and you will receive the exception.

[http://stackoverflow.com/questions/42528609/org-reflections-reflectionsexception-cant-resolve-member-named-for-class](http://stackoverflow.com/questions/42528609/org-reflections-reflectionsexception-cant-resolve-member-named-for-class)