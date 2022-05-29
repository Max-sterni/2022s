
all: ls_wc ls_grep_wc

.phony: clean

ls_wc: ls_wc.c
	gcc -o ls_wc ls_wc.c

ls_grep_wc: ls_grep_wc.c
	gcc -o ls_grep_wc ls_grep_wc.c

clean:
	rm ls_wc ls_grep_wc