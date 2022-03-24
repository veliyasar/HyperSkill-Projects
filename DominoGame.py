import random

turn_player = False
snake = []
# setting pieces
stock_pieces = [[i, j] for i in range(7) for j in range(i, 7)]
random.shuffle(stock_pieces)
computer_pieces = stock_pieces[:7]
player_pieces = stock_pieces[7:14]
stock_pieces = stock_pieces[14:]


def find_max_piece(_list):
    largest = [0, 0]
    for i in range(len(_list) - 1):
        if max(_list[i], _list[i + 1]) > largest:
            largest = max(_list[i], _list[i + 1])
    return largest


def print_status():
    print("\nStatus: It's your turn to make a move. Enter your command."
          if turn_player else
          "\nStatus: Computer is about to make a move. Press Enter to continue...")


def check_win():
    if len(player_pieces) == 0 and len(computer_pieces) == 0:
        print("Status: The game is over. It's a draw!")
        exit()
    elif len(player_pieces) == 0:
        print("Status: The game is over. You won!")
        exit()
    elif len(computer_pieces) == 0:
        print("Status: The game is over. The computer won!")
        exit()


# determining which player will make the first move
def determine_first_move(_turn_player):
    max_computer = find_max_piece(computer_pieces)
    max_player = find_max_piece(player_pieces)
    if max_player > max_computer:
        snake.append(max_player)
        player_pieces.remove(max_player)
        _turn_player = True
    elif max_computer > max_player:
        snake.append(max_computer)
        computer_pieces.remove(max_computer)
        _turn_player = False



def insert_to_snake(_choice, c_p_pieces):
    if _choice < 0:  # insert to the left
        snake.insert(0, c_p_pieces[abs(_choice) - 1])
        c_p_pieces.pop(abs(_choice) - 1)
    elif _choice == 0:
        c_p_pieces.append(stock_pieces[-1])
        stock_pieces.pop(-1)
    else:  # insert to the right
        snake.append(c_p_pieces[_choice - 1])
        c_p_pieces.pop(_choice - 1)


def display():
    print("=" * 70)
    print(f"Stock size: {len(stock_pieces)}")
    print(f"Computer pieces: {len(computer_pieces)}")
    # print snake
    print()
    if len(snake) > 6:
        print(f"{snake[0]}{snake[1]}{snake[2]}...{snake[-3]}{snake[-2]}{snake[-1]}")
    else:
        for i in range(len(snake)):
            print(snake[i], end="")
    print("\n")
    # print player pieces
    print("Your pieces: ")
    for i in range(len(player_pieces)):
        print(f"{i + 1}:{player_pieces[i]}")


determine_first_move(turn_player)
while True:
    display()
    check_win()
    print_status()

    if turn_player:
        # get player's choice of pieces
        while True:
            choice = input()
            try:
                if -6 <= int(choice) <= 6:
                    choice = int(choice)
                    break
                else:
                    raise ValueError
            except ValueError:
                print("Invalid input. Please try again.")
        insert_to_snake(choice, player_pieces)
        turn_player = False
    else:
        # expecting user to hit enter
        while True:
            enter = input()
            if enter == "":
                break
        insert_to_snake(random.randint(-len(computer_pieces), len(computer_pieces)),
                        computer_pieces)
        turn_player = True
