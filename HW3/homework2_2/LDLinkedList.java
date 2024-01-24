package homework2_2;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;


public class LDLinkedList<E> extends AbstractList<E> implements List<E> 
{
	private Node<E> head;
	private int size;
	private int numberOfLazyDeleted;
	
	public LDLinkedList()
	{
		head = null;
		size = 0;
		numberOfLazyDeleted = 0;
	}
	
	@Override
	public boolean add(E data)
	{
		add(size, data);
		return true;
	}
	
	@Override
	public void add(int index, E data)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node <E> newNode = new Node<E>(data);
        
        if(head == null)
        {
        	//System.out.println("head eklendi");
        	head = newNode;
        }
        else if(index == 0)
        {
        	//System.out.println("0. indexe " + data + " eklendi.");
        	newNode.next = head;
        	head = newNode;
        }
        else
        {
        	//System.out.println("düz eklendi");
        	Node<E> position = head;
        	
        	for(int i=0; i < index-1 && position != null ; i++)
        	{
        		position = position.next;
        	}
        	
        	newNode.next = position.next;
        	position.next = newNode;
        }
        size++;
    }
	
	@Override
	public int size() 
	{
		return size - numberOfLazyDeleted;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) 
	{
		if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
		
		int i = 0;
		int sayac =0 ;
		Node<E> position = head;
		
		while( position != null)
		{
			if(!position.LaziliyDeleted())
			{
				if(i == index)
				{
					//System.out.printf("index: %d --->", sayac);
					return position.getData();
				}
				i++;
			}
			else
			{
				System.out.println("Lazilit deleted: " + position.getData());
			}
			sayac ++;
			position = position.next;
		}
		throw new IndexOutOfBoundsException("Index out of range: " + index);
	}
	
	private boolean checkDeleted()
	{
		if(numberOfLazyDeleted == 1)
		{
			numberOfLazyDeleted = 0;
			return true;
		}
		else if(numberOfLazyDeleted == 0)
		{
			return false;
		}
		return false;
	}
	
	private E delete(int index)
	{
		Node<E> position = head;
		E dataTemp;
		if (index == 0) {
	        head = head.next;
	        size--;
	        return position.getData();
	    }
	    for (int i = 0; i < index - 1; i++) {
	        position = position.next;
	    }
	    //System.out.println("deletedeki i: " + index);
	    dataTemp = position.getData();
	    position.next = position.next.next;
	    size--;
	    return dataTemp;
	}
	
	private E LaziliyDelete(int index)
	{
		Node<E> position = head;
		int i;
		for(i=0; i < index && position != null ; i++)
    	{
    		position = position.next;
    	}
		position.setBool();
		//System.out.println(i + ". index marked");
		numberOfLazyDeleted += 1;
		return position.getData();
	}
	
	private int realIndex(int index)
	{
		int counter = 0;
		int i=0;
		Node<E> position = head;
		
		while( position != null)
		{
			if(!position.LaziliyDeleted())
			{
				if(counter == index)
				{
					//System.out.println("real index of " + index+ " i: " + i);
					return i;
				}
				counter++;
			}
			position = position.next;
			i++;
		}
    	
		throw new IndexOutOfBoundsException("Index out of range: " + index);
	}
	
	@Override
	public E remove(int index)
	{
		index = realIndex(index);
		if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
		
		Node<E> position = head;
		E temp = null;
		int i;

		if(checkDeleted())
		{
			for(i=0; i < index && position != null ; i++)
        	{
        		position = position.next;
        	}
			
			//System.out.println(position.getData() + " silinecek");
			delete(i);
			
			position = head;
			for(i=0; i<size && position != null; i++)
			{
				if(position.LaziliyDeleted())
				{
					temp = position.getData();
					//System.out.println(position.getData() + " marklı silinecek");
					delete(i);
				}
				position = position.next;
			}
			return temp;
		}
		else
		{
        	return LaziliyDelete(index);
		}
	}

	@Override
	public E set(int index, E data) {
		if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
		
		Node<E> position = head;
		E temp;
		for(int i=0; i < index && position != null ; i++)
    	{
    		position = position.next;
    	}
		temp = position.data;
		position.data = data;
		return temp;
	}
	

	private static class Node<E>
	{
		E data;
		Node<E> next;
		boolean isDeleted;
		
		private Node(E data)
		{
			this.data = data;
			next = null;
			isDeleted = false;
		}
		
		public E getData()
		{
			return data;
		}
		
		public void setBool()
		{
			isDeleted = true;
		}
		
		public boolean LaziliyDeleted()
		{
			return isDeleted;
		}
	}

}
