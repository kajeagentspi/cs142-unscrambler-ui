all: lib/unscrambler.c
	gcc -o core/assets/unscrambler.exe lib/unscrambler.c
	gradle run
clean: 
	$(RM) unscrambler.exe