package me.TheBukor.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R1.NBTTagCompound;

public class ExprTagOfv1_8_R1 extends SimpleExpression<Object> {
	private Expression<String> string;
	private Expression<NBTTagCompound> compound;
	private Object[] returned;
	@Override
	public Class<? extends Object> getReturnType() {
		return Object.class;
	}
	@Override
	public boolean isSingle() {
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		string = (Expression<String>) expr[0];
		compound = (Expression<NBTTagCompound>) expr[1];
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "the tag " + string.toString(e, false) + " of compound";
	}

	@Override
	@Nullable
	protected Object[] get(Event e) {
		NBTTagCompound NBT = compound.getSingle(e);
		String tag = string.getSingle(e);
		if (NBT.get(tag) == null) {
			return null;
		}
		Byte id = NBT.get(tag).getTypeId();
		switch (id) {
		case 1:
			returned = new Object[] { NBT.getByte(tag) };
			break;
		case 2:
			returned = new Object[] { NBT.getShort(tag) };
			break;
		case 3:
			returned = new Object[] { NBT.getInt(tag) };
			break;
		case 4:
			returned = new Object[] { NBT.getLong(tag) };
			break;
		case 5:
			returned = new Object[] { NBT.getFloat(tag) };
			break;
		case 6:
			returned = new Object[] { NBT.getDouble(tag) };
			break;
		case 8:
			returned = new Object[] { NBT.getString(tag) };
			break;
		case 9:
			returned = null;
			break;
		case 10:
			returned = new Object[] { NBT.getCompound(tag) };
			break;
		case 11:
			returned = null;
			break;
		default:
			break;
		}
		return returned;
	}
}