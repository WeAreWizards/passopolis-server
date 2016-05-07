# Building the binary

```console
$ nix-shell
$ ant crypto
$ ant jar
```

# Clean audit

Audit tables get very large, so the following has to run in a cron job
every so often. Better would be to have a date limit to only archive
old data but the following is betted than a full disk:

```
psql mitro -c "copy audit to '/tmp/audit.csv'; delete from audit;"
xz /tmp/audit.csv
mv /tmp/audit.csv.xz /var/log/audit-$(date -I).csv.xz
```
