package com.upgrad;

import java.util.Scanner;

/**
 * Part B:
 *
 * You are given an array of integers that represents the IDs of the users that have registered in “UpgradBook.”
 *
 * The array of integers have the following properties:
 *
 * The array is sorted is an ascending order
 *
 * The array holds distinct integers (i.e. there are no repeating numbers)
 *
 * The array is 1-indexed, i.e. the first element is stored in A[1] (not A[0])
 *
 * However the length of the array, N, is unknown (i.e. you don’t know how long the array is and arrayName.length
 * is not available to you). In short, you can not use the length of the array to apply binary search
 *
 * Since the length of the array is unknown, an error “ArrayIndexOutOfBoundsException” is returned
 * if you try to index into the array with an integer greater than N.
 *
 * Find a function to find positive integer M in the array A and write a program to find M (if M exists)
 * in O(log N) time.
 *
 * If M exists, please print the index of M using System.out.println().
 *
 * If M does not exist, please print the String "NOT_FOUND" using System.out.println().
 */
public class SolutionB {

    /**
     * This method returns the input array by the user defaulting the first element as 0 as array is 1-indexed
     * @param arr: the input array
     * @param l: the start index of the array
     * @param r: the end index of the array
     * @param x: the key that needs to be searched in the array
     * @return the index of the array where key is found else return -1
     */
    public static int binarySearch(int arr[], int l, int r, int x)
    {
        //If the end index is greater or equal to the start index of the array
        if (r>=l)
        {
            //Calculate the mid value from start and end index
            int mid = l + (r - l)/2;

            //If element in the array equals the key return the index of the array
            if (arr[mid] == x)
                return mid;

            //If the element at mid index in the array is greater then the key, search on the left side
            if (arr[mid] > x)
                return binarySearch(arr, l, mid-1, x);

            //If the element at mid index in the array is less then the key, search on the right side
            return binarySearch(arr, mid+1, r, x);
        }

        //If element not found return -1
        return -1;
    }

    // Method takes an infinite size array and a key to be
    // searched and returns its position if found else -1.
    // We don't know size of arr[] and we can assume size to be
    // infinite in this function and we cannot use length of the array method
    /**
     * This method calculates the upper bound on the array to apply binary search
     * @param arr: the input array
     * @param size: the size of the array
     * @param x: the key that needs to be searched in the array
     * @return the index of the array where key is found else return -1
     */
    public static int findPosition(int arr[], int size, int key)
    {
        int low = 1, high = 1;
        int val = arr[1];

        // Find high to do binary search
        while (val < key)
        {
            //Store previous high
            low = high;
            //Check that 2*high doesn't exceeds array length to prevent ArrayOutOfBoundException
            if(2*high < size-1)
                high = 2 * high;
            else
                high = size - 1;
            //Update new val
            val = arr[high];

            //Edge condition if the user enters a value greater then the maximum element in the array
            if(val != key && high == size-1)
                break;
        }

        //At this point we have updated low and high indices, thus use binary search between them
        return binarySearch(arr, low, high, key);
    }
    /**
     * This utility method returns the size of the array as we cannot use the length method in the array
     * @param size: the input array
     * @return the size of the array
     */
    public static int getArraySize(int arr[]){
        int size = 0;
        //Loop over the array
        for(int i: arr){
            //Increment the counter
            size++;
        }
        return size;
    }
    /**
     * This method returns the input array by the user defaulting the first element as 0 as array is 1-indexed
     * @param size: the size of the array
     * @param s: the scanner object to get the integer array elements from the user
     * @return the array
     */
    public static int[] getArrayInput(int size, Scanner s){

        int array[] = new int[size];

        //Default dummy value for first element as array is 1-indexed
        array[0] = 0;

        //Store the elements in the array given by the user
        for (int i = 1; i < size; i++) {
            array[i] = s.nextInt();
        }
        //Return the populated array
        return array;
    }
    //Default execution of the program
    public static void main(String[] args) {
        //Scanner object to get the input from the user via command line
        Scanner scanner = new Scanner(System.in);

        //Initialize the result variable
        int result = 0;

        //Get the size of the array from the user
        int sizeArray = scanner.nextInt();

        //Array size increased by 1 to consider all the elements as array is 1-indexed
        sizeArray++;

        //Get the key that needs to be searched in the array from the user
        int key = scanner.nextInt();

        //Get the sorted array as input from the user
        int sortedArray[] = getArrayInput(sizeArray, scanner);

        //Get the size of the array without using length method to avoid ArrayOutOfBoundsException
        int arraySize = getArraySize(sortedArray);

        //Binary Search the element where element is equal to the key
        if(arraySize > 1) {
            result = findPosition(sortedArray, arraySize, key);
        }else{
            result = -1;
        }

        //Element not found
        if (result == -1)
            System.out.println("NOT_FOUND");
            //If element found, print it
        else
            System.out.println(result);

    }
}